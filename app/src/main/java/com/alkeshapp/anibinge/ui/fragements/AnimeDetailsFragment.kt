package com.alkeshapp.anibinge.ui.fragements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkeshapp.anibinge.R
import com.alkeshapp.anibinge.databinding.FragmentAnimeDetailsBinding
import com.alkeshapp.anibinge.databinding.FragmentTopAnimeListBinding
import com.alkeshapp.anibinge.ui.adapters.TopAnimeAdapter
import com.alkeshapp.anibinge.ui.viewmodel.AnimeViewModel
import com.alkeshapp.anibinge.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailsFragment : Fragment() {
    private var animeId: Int? = null
    private lateinit var binding: FragmentAnimeDetailsBinding
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
    }

    private fun animeDetailObserver() {
        viewModel._animeDetailLiveData.observe(viewLifecycleOwner){ result ->
            when(result.status){
                Status.SUCCESS -> {
                    val anime = result.data?.AnimeObject
                    binding.title.text = anime?.title
                    binding.synopsisText.text = anime?.synopsis
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

    companion object {
        const val ANIME_ID = "anime_id"
    }
}