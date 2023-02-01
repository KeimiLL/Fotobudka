package com.mobilne.foto_zabawa.repository

import com.mobilne.foto_zabawa.model.TestResponse
import com.mobilne.foto_zabawa.network.ApiInterface
import com.mobilne.foto_zabawa.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TestRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getUserResponse(): Resource<TestResponse> {
        val response = try {
            apiInterface.getStatusTest()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}