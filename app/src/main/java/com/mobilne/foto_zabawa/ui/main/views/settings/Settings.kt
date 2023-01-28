package com.mobilne.foto_zabawa.ui.main.views.settings

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilne.foto_zabawa.ui.main.mainViewModel

@Composable
fun SettingsView(mainViewModel: mainViewModel) {
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
            SettingsElement("Czas do pierwszego zdjęcia(s): ", 10)
            SettingsElement("Czas między zdjęciami(s): ", 3)
            SettingsElement("Ilość zdjęć: ", 5)
        }
    }
}

@Composable
fun SettingsElement(
    name: String,
    number: Int
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
                .padding(vertical = 0.dp, horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SettingsButton("-")
            SettingsButton("+")
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(vertical = 0.dp, horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Text(text = number.toString(), fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
        }
    }
}

@Composable
fun SettingsButton(
    name: String,
) {
    Column() {
        OutlinedButton(
            onClick = {
//                Todo
            },
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            border = BorderStroke(4.dp, Color(0xff004f88)),
            contentPadding = PaddingValues(1.dp),

            ) {
            Text(
                text = name,
                color = Color(0xff004f88),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
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
    SettingsView(mainViewModel())
}