package com.mobilne.foto_zabawa.ui.main.views.camera

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.net.toUri
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mobilne.foto_zabawa.ui.main.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalPermissionsApi::class, ExperimentalCoilApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun CameraView(mainViewModel: MainViewModel) {
    Surface(
        modifier = Modifier
            .background(Color(0xFFc5ddf6))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainContent(Modifier.fillMaxSize(), mainViewModel)
        }
    }
}

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun MainContent(modifier: Modifier = Modifier, mainViewModel: MainViewModel) {
    val emptyImageUri = Uri.parse("file://dev/null")
    var imageUri by remember { mutableStateOf(emptyImageUri) }
    CameraCapture(
        modifier = modifier,
        onImageFile = { file ->
            imageUri = file.toUri()
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.postPhotoTest(file)
            }
        },
        mainViewModel = mainViewModel
    )
}