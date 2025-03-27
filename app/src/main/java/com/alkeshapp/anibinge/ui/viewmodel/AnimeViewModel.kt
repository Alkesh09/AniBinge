package com.alkeshapp.anibinge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkeshapp.anibinge.data.models.AnimeDetailsResponse
import com.alkeshapp.anibinge.data.models.TopAnimeResponse
import com.alkeshapp.anibinge.domain.usecases.GetAnimeDetailUseCase
import com.alkeshapp.anibinge.domain.usecases.GetTopAnimeUseCase
import com.alkeshapp.anibinge.utils.FlowResponse
import com.alkeshapp.anibinge.utils.FlowResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnimeUseCase,
    private val getAnimeDetailUseCase: GetAnimeDetailUseCase,
) : ViewModel() {

    private var topAnimeResponseLiveData = MutableLiveData<FlowResponse<TopAnimeResponse>>()
    val _topAnimeResponseLiveData: LiveData<FlowResponse<TopAnimeResponse>> =
        topAnimeResponseLiveData

    private var animeDetailLiveData = MutableLiveData<FlowResponse<AnimeDetailsResponse>>()
    val _animeDetailLiveData: LiveData<FlowResponse<AnimeDetailsResponse>> =
        animeDetailLiveData


    fun getTopAnimeList() {
        viewModelScope.launch {
            getTopAnimeUseCase.invoke().collect { result ->
                val response = when (result) {
                    is FlowResult.Success -> FlowResponse.success(result.data)
                    is FlowResult.Failure -> FlowResponse.error(result.msg)
                    is FlowResult.Loading -> FlowResponse.loading()
                }
                topAnimeResponseLiveData.postValue(response)
            }
        }
    }

    fun getAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            getAnimeDetailUseCase.invoke(animeId).collect { result ->
                val response = when (result) {
                    is FlowResult.Success -> FlowResponse.success(result.data)
                    is FlowResult.Failure -> FlowResponse.error(result.msg)
                    is FlowResult.Loading -> FlowResponse.loading()
                }
                animeDetailLiveData.postValue(response)
            }
        }
    }
}