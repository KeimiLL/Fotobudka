package com.mobilne.foto_zabawa.ui.main.views.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsView() {
    Box(
        modifier = Modifier
            .background(Color(0xFFc5ddf6))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            SettingsElement("Czas do pierwszego zdjęcia: 10s")
            SettingsElement("Czas między zdjęciami: 5s")
            SettingsElement("Ilość zdjęć: 5")
        }
    }
}

@Composable
fun SettingsElement(
    name: String
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(vertical = 0.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "-")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "+")
            }
        }
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 25.sp)

    }
}


@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun DefaultPreview() {
    SettingsView()
}