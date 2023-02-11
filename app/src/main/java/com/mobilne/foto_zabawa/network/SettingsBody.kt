package com.mobilne.foto_zabawa.network


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SettingsBody(
    @SerializedName("cardId")
    val cardId: Int,
    @SerializedName("cardText")
    val cardText: String
)