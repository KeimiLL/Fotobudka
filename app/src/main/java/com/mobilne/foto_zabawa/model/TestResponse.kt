package com.mobilne.foto_zabawa.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TestResponse(
    @SerializedName("settings")
    val settings: Settings,
    @SerializedName("photos")
    val photos: List<String>
)