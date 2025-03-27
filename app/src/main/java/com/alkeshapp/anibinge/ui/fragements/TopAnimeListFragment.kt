package com.alkeshapp.anibinge.ui.fragements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkeshapp.anibinge.R
import com.alkeshapp.anibinge.databinding.FragmentTopAnimeListBinding
import com.alkeshapp.anibinge.ui.adapters.TopAnimeAdapter
import com.alkeshapp.anibinge.ui.viewmodel.AnimeViewModel
import com.alkeshapp.anibinge.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAnimeListFragment : Fragment(), TopAnimeAdapter.OnAnimeItemClickListener {

    private lateinit var binding: FragmentTopAnimeListBinding
    private lateinit var navController: NavController
    private val viewModel: AnimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_top_anime_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopAnimeList()
        navController = findNavController()
        topAnimeListObserver()
    }

    private fun topAnimeListObserver() {
        viewModel._topAnimeResponseLiveData.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    val animeList = result.data?.animeList ?: listOf()
                    val adapter = TopAnimeAdapter(animeList, this)
                    val layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.rvTopAnime.layoutManager = layoutManager
                    binding.rvTopAnime.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    if(!viewModel.isDataLoaded)
                        binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onAnimeItemClick(animeId: Int) {
        val bundle = bundleOf(AnimeDetailsFragment.ANIME_ID to animeId)
        if (navController.currentDestination?.id == R.id.topAnimeListFragment) {
            navController.navigate(
                R.id.action_topAnimeListFragment_to_animeDetailsFragment,
                bundle
            )
        }
    }
}