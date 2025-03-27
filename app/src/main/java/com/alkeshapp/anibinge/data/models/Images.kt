package com.alkeshapp.anibinge.data.models


import com.alkeshapp.anibinge.data.models.Jpg
import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("jpg")
    val jpg: Jpg?
)