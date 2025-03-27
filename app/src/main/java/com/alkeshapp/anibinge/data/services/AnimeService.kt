package com.alkeshapp.anibinge.data.services

import com.alkeshapp.anibinge.data.models.AnimeDetailsResponse
import com.alkeshapp.anibinge.data.models.TopAnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface AnimeService {

    @GET("top/anime")
    suspend fun getTopAnimes(): Response<TopAnimeResponse>

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Path("anime_id") animeId: Int
    ): Response<AnimeDetailsResponse>

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }
}