package com.alkeshapp.anibinge.domain.repository

import com.alkeshapp.anibinge.data.models.AnimeDetailsResponse
import com.alkeshapp.anibinge.data.models.TopAnimeResponse
import retrofit2.Response

interface AnimeRepository {
    suspend fun getTopAnimeList(): Response<TopAnimeResponse>

    suspend fun getAnimeDetails(animeId: Int): Response<AnimeDetailsResponse>
}