package com.alkeshapp.anibinge.data.models


import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("images")
    val images: TralierImages?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?
)