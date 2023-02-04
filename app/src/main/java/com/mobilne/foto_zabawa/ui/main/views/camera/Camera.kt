package com.mobilne.foto_zabawa.ui.main.views.camera

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.net.toUri
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mobilne.foto_zabawa.ui.main.views.camera.CameraCapture
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

//@ExperimentalCoilApi
//@ExperimentalCoroutinesApi
//@ExperimentalPermissionsApi
@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoilApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun CameraView() {
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
            MainContent(Modifier.fillMaxSize())
        }
    }
}

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val emptyImageUri = Uri.parse("file://dev/null")
    var imageUri by remember { mutableStateOf(emptyImageUri) }
    if (imageUri != emptyImageUri) {
        Box(modifier = modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(imageUri),
                contentDescription = "Captured image"
            )
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    imageUri = emptyImageUri
                }
            ) {
                Text("Remove image")
            }
        }
    } else {
        CameraCapture(
            modifier = modifier,
            onImageFile = { file ->
                imageUri = file.toUri()
            }
        )
    }
}
//
//@Preview(
//    showBackground = true,
//    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
//)
//@Composable
//fun DefaultPreview() {
//    CameraView()
//}