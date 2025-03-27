package com.alkeshapp.anibinge.data.repository

import com.alkeshapp.anibinge.data.models.AnimeDetailsResponse
import com.alkeshapp.anibinge.data.models.TopAnimeResponse
import com.alkeshapp.anibinge.data.services.AnimeService
import com.alkeshapp.anibinge.domain.repository.AnimeRepository
import retrofit2.Response
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(private val animeService: AnimeService) :
    AnimeRepository {
    override suspend fun getTopAnimeList(): Response<TopAnimeResponse> {
        return animeService.getTopAnimes()
    }

    override suspend fun getAnimeDetails(animeId: Int): Response<AnimeDetailsResponse> {
        return animeService.getAnimeDetails(animeId = animeId)
    }
}