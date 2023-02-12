package com.mobilne.foto_zabawa.repository

import com.mobilne.foto_zabawa.network.ApiInterface
import com.mobilne.foto_zabawa.network.PDFResponse
import com.mobilne.foto_zabawa.network.SettingsBody
import com.mobilne.foto_zabawa.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

@ActivityScoped
class TestRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun postSettings(
        id: String,
        cardId: Int,
        cardText: String
    ): Resource<ResponseBody> {
        val response = try {
            apiInterface.postSettings(id, SettingsBody(cardId, cardText))
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

    suspend fun postPhotoTest(
        id: String,
        imagePart: MultipartBody.Part,
        cardId: RequestBody,
    ): Resource<ResponseBody> {
        val response = try {
            apiInterface.postPhotoTest(id, imagePart, cardId)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

    suspend fun getPDFUrl(
        id: String,
    ): Resource<PDFResponse> {
        val response = try {
            apiInterface.getPDFUrl(id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}