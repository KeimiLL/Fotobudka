package com.mobilne.foto_zabawa.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @Multipart
    @POST("photo/test")
    suspend fun postPhotoTest(
        @Part imagePart: MultipartBody.Part,
        @Part("cardId") cardId: RequestBody,
        @Part("cardText") cardText: RequestBody,
    ): ResponseBody
}