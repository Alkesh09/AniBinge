package com.alkeshapp.anibinge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkeshapp.anibinge.data.models.TopAnimeResponse
import com.alkeshapp.anibinge.domain.usecases.GetTopAnimeUseCase
import com.alkeshapp.anibinge.utils.FlowResponse
import com.alkeshapp.anibinge.utils.FlowResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val getTopAnimeUseCase: GetTopAnimeUseCase) :
    ViewModel() {

    private var topAnimeResponseLiveData = MutableLiveData<FlowResponse<TopAnimeResponse>>()
    val _topAnimeResponseLiveData: LiveData<FlowResponse<TopAnimeResponse>> =
        topAnimeResponseLiveData

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
}