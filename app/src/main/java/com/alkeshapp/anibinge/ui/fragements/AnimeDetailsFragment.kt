package com.alkeshapp.anibinge.ui.fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alkeshapp.anibinge.R
import com.alkeshapp.anibinge.databinding.FragmentAnimeDetailsBinding
import com.alkeshapp.anibinge.ui.viewmodel.AnimeViewModel
import com.alkeshapp.anibinge.utils.Status
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailsFragment : Fragment() {
    private var animeId: Int? = null
    private lateinit var binding: FragmentAnimeDetailsBinding
//    private var player: ExoPlayer? = null
//    private lateinit var playerView: PlayerView
    private val viewModel: AnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            animeId = it.getInt(ANIME_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anime_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAnimeDetails(animeId ?: 0)
        animeDetailObserver()
        lifecycle.addObserver(binding.videoPlayer)

        binding.IVposter.setOnClickListener {
            binding.IVposter.visibility = View.GONE
//            player?.play()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.videoPlayer.release()
//        player?.release()
//        player = null
    }

    private fun createPlayer(videoId: String){
        binding.videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }

    private fun animeDetailObserver() {
        viewModel._animeDetailLiveData.observe(viewLifecycleOwner){ result ->
            when(result.status){
                Status.SUCCESS -> {
                    val anime = result.data?.AnimeObject
                    val genres = anime?.genres?.map { g -> g.name } ?: listOf()
                    addGenres(genres)
                    if(anime?.trailer?.youtubeId.isNullOrBlank()){
                        Glide.with(requireContext()).load(anime?.trailer?.images?.largeImageUrl).into(binding.IVposter)
                        binding.IVposter.visibility = View.VISIBLE
                    }else{
                        createPlayer( anime?.trailer?.youtubeId ?: "")
                    }
                    binding.title.text = anime?.title
                    binding.synopsisText.text = anime?.synopsis
                    binding.genreTitle.text = context?.getString(R.string.genres)
                    binding.synopsisTitle.text = context?.getString(R.string.plot)
                    binding.noOfEpisodes.text = "Episodes \n ${anime?.episodes}"
                    binding.rating.text = "Rating \n ${anime?.score}"

                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun addGenres(genreList: List<String?>){
        for (genre in genreList) {
            val chip = Chip(requireContext()).apply {
                text = genre ?: ""
                isCheckable = false
                isClickable = false
                setChipBackgroundColorResource(R.color.orange_black)
                setTextColor(resources.getColor(R.color.white, null))
            }
            binding.chipGroupGenres.addView(chip)
        }
    }
    companion object {
        const val ANIME_ID = "anime_id"
    }
}