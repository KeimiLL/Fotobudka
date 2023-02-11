package com.mobilne.foto_zabawa.ui.main

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilne.foto_zabawa.R
import com.mobilne.foto_zabawa.repository.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask


@HiltViewModel
class MainViewModel @Inject constructor(
    private val testRepository: TestRepository,
) : ViewModel() {

    suspend fun postSettings() = viewModelScope.launch {
        testRepository.postSettings(seriesUUID, currentCardId, currentCardText)
    }

    suspend fun postPhotoTest(file: File) = viewModelScope.launch {
        val reqFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("file", file.name, reqFile)
        val cardId: RequestBody =
            currentCardId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val response = testRepository.postPhotoTest(
            seriesUUID,
            image,
            cardId
        ).data?.string()
        if (response == "OK") apiResponseCount++
        if (apiResponseCount == photosCount) {
            pdfUrl = testRepository.getPDFUrl(seriesUUID).data!!.pdf
            delay(1000L)
            resetApiResponseCount()
            delay(2000L)
            resetPDFUrl()
        }
    }

    //camera
    var isButtonEnable by mutableStateOf(true)

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

    var seriesUUID = ""
    var apiResponseCount by mutableStateOf(0)
    var pdfUrl by mutableStateOf("")

    private fun getUUID(): String {
        return UUID.randomUUID().toString()
    }

    fun setNewSeriesUUID() {
        seriesUUID = getUUID()
    }

    fun resetApiResponseCount() {
        apiResponseCount = 0
    }

    fun resetPDFUrl() {
        pdfUrl = ""
    }

    fun getApiResponseCountDisplayText(): String {
        return if (isButtonEnable && apiResponseCount == 0) "" else "${if (language) "Wysyłanie zdjęć" else "Sending photos"}:\n$apiResponseCount/$photosCount"
    }

    fun getPDFUrlDisplayText(): String {
        return if (pdfUrl.isEmpty()) "" else if (language) "Pobierz PDF" else "Download PDF"
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

    fun getValue(index: Int): Int {
        when (index) {
            0 -> {
                return timeFirstPhoto
            }
            1 -> {
                return timeBetweenPhotos
            }
            2 -> {
                return photosCount
            }
        }
        return 0
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

    //liczy wszytskei działania
    fun disableButton() {
        isButtonEnable = false
        Timer().schedule(timerTask {
            isButtonEnable = true
        }, (((photosCount - 1) * timeBetweenPhotos + timeFirstPhoto) * 1000).toLong())
    }

    //tylko opóźnienie pierwszego zdjęcia
    fun disableButtonDelay() {
        isButtonEnable = false
        Timer().schedule(timerTask {
            isButtonEnable = true
        }, (timeFirstPhoto * 1000).toLong())
    }


    fun alertSound(context: Context) {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.alert)
        mp.start()
    }

    fun photoSound(context: Context) {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.casual)
        mp.start()
    }

    fun endSound(context: Context) {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.end)
        mp.start()
    }
}