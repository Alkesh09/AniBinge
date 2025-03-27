package com.alkeshapp.anibinge.ui.fragements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkeshapp.anibinge.R
import com.alkeshapp.anibinge.databinding.FragmentTopAnimeListBinding
import com.alkeshapp.anibinge.ui.adapters.TopAnimeAdapter
import com.alkeshapp.anibinge.ui.viewmodel.AnimeViewModel
import com.alkeshapp.anibinge.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class TopAnimeListFragment : Fragment() {

    private lateinit var binding: FragmentTopAnimeListBinding
    private val viewModel: AnimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_anime_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopAnimeList()
        topAnimeListObserver()
    }

    private fun topAnimeListObserver() {
        viewModel._topAnimeResponseLiveData.observe(viewLifecycleOwner){ result ->
            when(result.status){
                Status.SUCCESS -> {
                    val animeList = result.data?.animeList ?: listOf()
                    val adapter = TopAnimeAdapter(animeList)
                    val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.rvTopAnime.layoutManager = layoutManager
                    binding.rvTopAnime.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}