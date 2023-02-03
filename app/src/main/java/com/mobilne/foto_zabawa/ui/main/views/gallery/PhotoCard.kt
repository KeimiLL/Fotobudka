package com.mobilne.foto_zabawa.ui.main.views.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mobilne.foto_zabawa.ui.main.MainViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoCard(
    drawableId: Int,
    description: String,
    id: Int,
    mainViewModel: MainViewModel
    ) {
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .width(IntrinsicSize.Min)
            .padding(5.dp),
        elevation = 8.dp,
        onClick = {
            mainViewModel.currentCardId = id
            mainViewModel.currentCardText = description
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(140.dp)
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = description,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(
                        top = 16.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 16.dp
                    ),
            )
        }

    }
}