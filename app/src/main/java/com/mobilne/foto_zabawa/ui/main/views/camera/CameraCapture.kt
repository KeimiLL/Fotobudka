package com.mobilne.foto_zabawa.ui.main.views.camera

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mobilne.foto_zabawa.R
import com.mobilne.foto_zabawa.ui.main.MainViewModel
import com.mobilne.foto_zabawa.utils.Permission
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.io.File
import kotlin.concurrent.fixedRateTimer

@ExperimentalPermissionsApi
@ExperimentalCoroutinesApi
@Composable
fun CameraCapture(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    onImageFile: (File) -> Unit = { },
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    val stringForPermissionLanguage: Int
    val stringForNoPermissionLanguage: Int
    val stringForOpenSettings: Int
    if (!mainViewModel.language) {
        stringForPermissionLanguage = R.string.permission
        stringForNoPermissionLanguage = R.string.nopermission
        stringForOpenSettings = R.string.settings
    } else {
        stringForPermissionLanguage = R.string.pozwolenie
        stringForNoPermissionLanguage = R.string.niepozwolenie
        stringForOpenSettings = R.string.ustawienia
    }
    Permission(permission = Manifest.permission.CAMERA,
        rationale = stringResource(stringForPermissionLanguage),
        mainViewModel = mainViewModel,
        permissionNotAvailableContent = {
            Column(modifier) {
                Text(stringResource(stringForNoPermissionLanguage))
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    })
                }) {
                    Text(stringResource(stringForOpenSettings))
                }
            }
        }) {
        Box(modifier = modifier) {
            val lifecycleOwner = LocalLifecycleOwner.current
            val coroutineScope = rememberCoroutineScope()
            var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
            val imageCaptureUseCase by remember {
                mutableStateOf(
                    ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY).build()
                )
            }
            Box {
                CameraPreview(modifier = Modifier.fillMaxSize(), onUseCase = {
                    previewUseCase = it
                })
                CapturePictureButton(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                    onClick = {
                        mainViewModel.disableButton()
                        mainViewModel.setNewSeriesUUID()

                        // Interval
                        var counter = 0
                        fixedRateTimer(
                            name = "alert-timer",
                            initialDelay = 0,
                            period = 1000,
                            daemon = true
                        ) {

                            if (counter < mainViewModel.getValue(0) || (counter - mainViewModel.getValue(
                                    0
                                )) % mainViewModel.getValue(1) != 0
                            ) {
                                mainViewModel.alertSound(context = context)
                            }
                            if (mainViewModel.isButtonEnable) {
                                this.cancel()
                            }
                            counter++
                        }
                        fixedRateTimer(
                            name = "photo-timer",
                            initialDelay = (mainViewModel.getValue(0) * 1000).toLong(),
                            period = (mainViewModel.getValue(1) * 1000).toLong(),
                            daemon = true
                        ) {
                            mainViewModel.photoSound(context = context)
                            coroutineScope.launch {
                                imageCaptureUseCase.takePicture(context.executor).let {
                                    onImageFile(it)
                                    mainViewModel.postPhotoTest(it)
                                }
                            }
                            if (mainViewModel.isButtonEnable) {
                                mainViewModel.endSound(context = context)
                                this.cancel()
                            }
                        }
                    },
                    mainViewModel = mainViewModel
                )
                val background = if (mainViewModel.isButtonEnable && mainViewModel.apiResponseCount == 0) Color.Transparent else Color.LightGray
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(bottomStartPercent = 20))
                        .align(Alignment.TopStart)
                        .background(background)
                        .padding(8.dp)
                ) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = mainViewModel.getApiResponseCountDisplayText()
                    )
                }
            }
            LaunchedEffect(previewUseCase) {
                val cameraProvider = context.getCameraProvider()
                try {
                    // Must unbind the use-cases before rebinding them.
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
                    )
                } catch (ex: Exception) {
                    Log.e("CameraCapture", "Binding of camera use cases failed", ex)
                }
            }
        }
    }
}