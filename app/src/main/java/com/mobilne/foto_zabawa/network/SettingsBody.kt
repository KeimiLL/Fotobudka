package com.mobilne.foto_zabawa.network


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SettingsBody(
    @SerializedName("cardId")
    val cardId: Int,
    @SerializedName("cardText")
    val cardText: String
)