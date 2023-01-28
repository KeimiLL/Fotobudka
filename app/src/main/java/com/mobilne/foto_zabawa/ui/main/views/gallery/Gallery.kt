package com.mobilne.foto_zabawa.ui.main.views.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.foto_zabawa.R

@Composable
fun GalleryView() {
    Box(
        modifier = Modifier
            .background(Color(0xFFc5ddf6))
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PhotoCard(drawableId = R.drawable.settings, description = "Test")
            PhotoCard(drawableId = R.drawable.settings, description = "Urodziny")
            PhotoCard(drawableId = R.drawable.settings, description = "Wielkanoc")
            PhotoCard(drawableId = R.drawable.settings, description = "Zima")
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun DefaultPreview() {
    GalleryView()
}