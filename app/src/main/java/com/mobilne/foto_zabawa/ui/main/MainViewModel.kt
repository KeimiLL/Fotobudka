package com.mobilne.foto_zabawa.ui.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilne.foto_zabawa.model.TestResponse
import com.mobilne.foto_zabawa.repository.TestRepository
import com.mobilne.foto_zabawa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val testRepository: TestRepository,
) : ViewModel() {
    val testResponse: MutableState<Resource<TestResponse>?> = mutableStateOf(null)

    private suspend fun getTestResponse() = viewModelScope.launch {
        testResponse.value = testRepository.getStatusTest()
    }

    /**
     * should work once we have pictures available programmatically
     * waiting for the camera implementation */
    private suspend fun postPhotoTest() = viewModelScope.launch {
        val file = File("")
        val reqFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("file", file.name, reqFile)
        val cardId: RequestBody = "1".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val cardText: RequestBody = "Test".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        testRepository.postPhotoTest(
            image,
            cardId,
            cardText
        )

        Log.d("ContentValues", file.name)
        Log.d("ContentValues", image.toString())
        Log.d("ContentValues", cardId.toString())
        Log.d("ContentValues", cardText.toString())
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getTestResponse()
            postPhotoTest()
        }
    }

    //navigation
    var currentView by mutableStateOf("Settings")

    //gallery
    var currentCardId by mutableStateOf(0)
    var currentCardText by mutableStateOf("Noir")

    //settings
    private val maxWaitingTime = 10
    private var timeFirstPhoto by mutableStateOf(5)
    private var timeBetweenPhotos by mutableStateOf(3)
    private var photosCount by mutableStateOf(6)


    fun increaseSettingsValue(index: Int) {
        when (index) {
            0 -> {
                if (timeFirstPhoto != maxWaitingTime) timeFirstPhoto += 1
            }
            1 -> {
                if (timeBetweenPhotos != maxWaitingTime) timeBetweenPhotos += 1
            }
            2 -> {
                photosCount += 1
            }
        }
    }

    fun decreaseSettingsValue(index: Int) {
        when (index) {
            0 -> {
                if (timeFirstPhoto != 1) timeFirstPhoto -= 1
            }
            1 -> {
                if (timeBetweenPhotos != 1) timeBetweenPhotos -= 1
            }
            2 -> {
                if (photosCount != 2) photosCount -= 1
            }
        }
    }

    fun readValue(index: Int): String {
        when (index) {
            0 -> {
                return timeFirstPhoto.toString()
            }
            1 -> {
                return timeBetweenPhotos.toString()
            }
            2 -> {
                return photosCount.toString()
            }
        }
        return " "
    }

}