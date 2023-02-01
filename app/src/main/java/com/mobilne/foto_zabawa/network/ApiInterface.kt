package com.mobilne.foto_zabawa.network

import com.mobilne.foto_zabawa.model.TestResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("status/test")
    suspend fun getStatusTest(): TestResponse
}