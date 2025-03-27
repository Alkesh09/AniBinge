package com.alkeshapp.anibinge.data.models


import com.google.gson.annotations.SerializedName

data class AnimeObject(
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("images")
    val images: Images?,
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("members")
    val members: Int?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("season")
    val season: String?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("title_english")
    val titleEnglish: String?,
    @SerializedName("trailer")
    val trailer: Trailer?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("year")
    val year: Int?
)