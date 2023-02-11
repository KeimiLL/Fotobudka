package com.mobilne.foto_zabawa.network


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PDFResponse(
    @SerializedName("pdf")
    val pdf: String
)