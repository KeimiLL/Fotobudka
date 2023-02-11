package com.mobilne.foto_zabawa.network


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PDFResponse(
    @SerializedName("pdf")
    val pdf: String
)