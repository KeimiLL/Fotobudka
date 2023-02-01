package com.mobilne.foto_zabawa.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Settings(
    @SerializedName("cardId")
    val cardId: Int,
    @SerializedName("cardText")
    val cardText: String
)