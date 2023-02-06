package com.mobilne.foto_zabawa.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilne.foto_zabawa.repository.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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

    suspend fun postPhotoTest(file: File) = viewModelScope.launch {
        val reqFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("file", file.name, reqFile)
        val cardId: RequestBody =
            currentCardId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val cardText: RequestBody =
            currentCardText.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        testRepository.postPhotoTest(
            image,
            cardId,
            cardText
        )
        apiResponseCount++
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
    var language by mutableStateOf(false)

    // should be reset on every camera button click
    var apiResponseCount by mutableStateOf(0)

    fun resetApiResponseCount() {
        apiResponseCount = 0
    }

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

    fun changeLanguage() {
        language = !language
    }

    fun readLanguage(): String {
        return if (!language)
            "English"
        else
            "Polski"
    }
}