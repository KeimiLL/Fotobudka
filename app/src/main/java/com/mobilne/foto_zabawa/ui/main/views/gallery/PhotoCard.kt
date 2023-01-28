package com.mobilne.foto_zabawa.ui.main.views.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoCard(
    drawableId: Int,
    description: String,

    ) {
    Card(
        modifier = Modifier
            .height(230.dp)
            .width(IntrinsicSize.Min)
            .padding(5.dp),
        elevation = 8.dp,
        onClick = {
// Todo
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(140.dp)
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = description,
                modifier = Modifier
                    .height(80.dp)
                    .padding(
                        top = 16.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 16.dp
                    ),
            )
            Text(
                text = description,
                style = MaterialTheme.typography.h5,
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = description,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Justify
            )
        }

    }
}