package com.mobilne.foto_zabawa.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mobilne.foto_zabawa.R
import com.mobilne.foto_zabawa.ui.main.views.camera.CameraView
import com.mobilne.foto_zabawa.ui.main.views.gallery.GalleryView
import com.mobilne.foto_zabawa.ui.main.views.settings.SettingsView


@Composable
fun MainView(mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .background(Color(0xFFc5ddf6))
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color(0xffcccccc))
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                NavigationButton(R.drawable.settings, "Settings", mainViewModel)
                NavigationButton(R.drawable.camera, "Camera", mainViewModel)
                NavigationButton(R.drawable.gallery, "Gallery", mainViewModel)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                if (mainViewModel.currentView.contains("Settings"))
                    SettingsView(mainViewModel)
                else if (mainViewModel.currentView.contains("Gallery"))
                    GalleryView()
                else if (mainViewModel.currentView.contains("Camera"))
                    CameraView()
            }
        }
    }
}

@Composable
fun NavigationButton(
    icon: Int,
    name: String,
    mainViewModel: MainViewModel
) {
    // Fetching the local context
    val mContext = LocalContext.current
    Column() {
        OutlinedButton(
            onClick = {
                mainViewModel.currentView = name
            },
            modifier = Modifier.size(70.dp),
            shape = CircleShape,
            border = BorderStroke(5.dp, Color(0xff004f88)),
            contentPadding = PaddingValues(1.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
        ) {
            // Adding an Icon "Add" inside the Button
            Image(
                painterResource(id = icon),
                contentDescription = name,
                Modifier.size(30.dp),
            )
        }

    }
}


@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun DefaultPreview() {
    MainView(MainViewModel())
}