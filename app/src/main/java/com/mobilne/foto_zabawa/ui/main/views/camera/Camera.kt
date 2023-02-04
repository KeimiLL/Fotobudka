package com.mobilne.foto_zabawa.ui.main.views.camera

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mobilne.foto_zabawa.utils.Permission
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalPermissionsApi::class)
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

@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Permission(
        permission = Manifest.permission.CAMERA,
        rationale = "You said you wanted a picture, so I'm going to have to ask for permission.",
        permissionNotAvailableContent = {
            Column(modifier) {
                androidx.compose.material.Text("O noes! No Camera!")
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                        )
                    }
                ) {
                    androidx.compose.material.Text("Open Settings")
                }
            }
        }
    ) {
        CameraCapture(modifier)
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun DefaultPreview() {
    CameraView()
}