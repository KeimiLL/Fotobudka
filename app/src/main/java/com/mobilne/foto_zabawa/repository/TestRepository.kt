package com.mobilne.foto_zabawa.repository

import com.mobilne.foto_zabawa.model.TestResponse
import com.mobilne.foto_zabawa.network.ApiInterface
import com.mobilne.foto_zabawa.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Part
import javax.inject.Inject

@ActivityScoped
class TestRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getStatusTest(): Resource<TestResponse> {
        val response = try {
            apiInterface.getStatusTest()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

    suspend fun postPhotoTest(
        @Part imagePart: MultipartBody.Part,
        @Part("cardId") cardId: RequestBody,
        @Part("cardText") cardText: RequestBody,
    ): Resource<ResponseBody> {
        val response = try {
            apiInterface.postPhotoTest(imagePart, cardId, cardText)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}