package com.mobilne.foto_zabawa.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @POST("settings/{id}")
    suspend fun postSettings(
        @Path("id") id: String,
        @Body requestBody: SettingsBody,
    ): ResponseBody

    @Multipart
    @POST("photo/{id}")
    suspend fun postPhotoTest(
        @Path("id") id: String,
        @Part imagePart: MultipartBody.Part,
        @Part("cardId") cardId: RequestBody,
    ): ResponseBody

    @GET("result/{id}")
    suspend fun getPDFUrl(
        @Path("id") id: String,
    ): PDFResponse
}