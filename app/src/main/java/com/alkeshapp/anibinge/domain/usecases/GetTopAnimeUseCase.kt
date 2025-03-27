package com.alkeshapp.anibinge.domain.usecases

import com.alkeshapp.anibinge.data.models.TopAnimeResponse
import com.alkeshapp.anibinge.domain.repository.AnimeRepository
import com.alkeshapp.anibinge.utils.FlowResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetTopAnimeUseCase @Inject constructor(private val animeRepository: AnimeRepository){

    suspend fun invoke(): Flow<FlowResult<TopAnimeResponse>> = flow {
        emit(FlowResult.Loading)
        val response = animeRepository.getTopAnimeList()
        when{
            response.isSuccessful->{
                if(response.body() != null){
                    emit(FlowResult.Success(data = response.body()!!))
                }else{
                    emit(FlowResult.Failure("Something Went Wrong"))
                }
            }
            else->{
                emit(FlowResult.Failure("Something Went Wrong"))
            }
        }
    }
}