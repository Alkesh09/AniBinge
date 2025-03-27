package com.alkeshapp.anibinge.data.services

import com.alkeshapp.anibinge.data.models.TopAnimeResponse
import retrofit2.Response
import retrofit2.http.GET


interface AnimeService {

    @GET("top/anime")
    suspend fun getTopAnimes(): Response<TopAnimeResponse>

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }
}