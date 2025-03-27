package com.alkeshapp.anibinge.data.models

import com.google.gson.annotations.SerializedName

data class AnimeDetailsResponse(
    @SerializedName("data")
    val AnimeObject: AnimeObject?
)