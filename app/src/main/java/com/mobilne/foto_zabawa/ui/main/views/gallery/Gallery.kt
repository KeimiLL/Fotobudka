package com.mobilne.foto_zabawa.ui.main.views.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.foto_zabawa.R
import com.mobilne.foto_zabawa.ui.main.MainViewModel

@Composable
fun GalleryView(mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .background(Color(0xFFc5ddf6))
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Aktualnie wybrany motyw: ",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "${mainViewModel.currentCardText}",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PhotoCard(
                    drawableId = R.drawable.noir,
                    description = "Noir",
                    id = 0,
                    mainViewModel = mainViewModel
                )
                PhotoCard(
                    drawableId = R.drawable.birthday,
                    description = "Urodziny",
                    id = 1,
                    mainViewModel = mainViewModel
                )
                PhotoCard(
                    drawableId = R.drawable.holiday,
                    description = "Wakacje",
                    id = 2,
                    mainViewModel = mainViewModel
                )
                PhotoCard(
                    drawableId = R.drawable.winter,
                    description = "Zima",
                    id = 3,
                    mainViewModel = mainViewModel
                )
            }
        }

    }
}

//@Preview(
//    showBackground = true,
//    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
//)
//@Composable
//fun DefaultPreview() {
//    GalleryView(mainViewModel = MainViewModel())
//}