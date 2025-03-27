package com.alkeshapp.anibinge.data.models


import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(
    @SerializedName("data")
    val animeList: List<AnimeObject>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)