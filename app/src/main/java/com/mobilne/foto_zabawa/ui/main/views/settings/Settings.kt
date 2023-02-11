package com.mobilne.foto_zabawa.ui.main.views.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilne.foto_zabawa.R
import com.mobilne.foto_zabawa.ui.main.MainViewModel

@Composable
fun SettingsView(mainViewModel: MainViewModel) {
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
            val time1: Int
            val time2: Int
            val count: Int
            val lang: Int
            if (mainViewModel.language) {
                time1 = R.string.czas_do
                time2 = R.string.czas_pomiedzy
                count = R.string.liczba_zdjec
                lang = R.string.jezyk
            } else {
                time1 = R.string.time_before
                time2 = R.string.time_between
                count = R.string.count
                lang = R.string.language
            }
            SettingsElement(
                name = time1,
                index = 0,
                mainViewModel = mainViewModel
            )
            SettingsElement(
                name = time2,
                index = 1,
                mainViewModel = mainViewModel
            )
            SettingsElement(
                name = count,
                index = 2,
                mainViewModel = mainViewModel
            )
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(vertical = 0.dp, horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = mainViewModel.language,
                    onCheckedChange = { mainViewModel.changeLanguage() },

                    modifier = Modifier
                        .width(100.dp)
                        .scale(2f, 2f)
                        .padding(vertical = 0.dp, horizontal = 30.dp),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xff004f88),
                        uncheckedThumbColor = Color(0xff004f88),
                        checkedTrackColor = Color.White,
                        uncheckedTrackColor = Color.White,
                        checkedTrackAlpha = 1.0f,
                        uncheckedTrackAlpha = 1.0f
                    )
                )
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(vertical = 0.dp, horizontal = 55.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(lang),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Text(
                        text = "  " + mainViewModel.readLanguage(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp,
                        color = Color(0xff004f88)
                    )
                }
            }

        }
    }
}

@Composable
fun SettingsElement(
    name: Int,
    index: Int,
    mainViewModel: MainViewModel,
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
            SettingsButton("-", mainViewModel, index)
            SettingsButton("+", mainViewModel, index)
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(vertical = 0.dp, horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(name), fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Text(
                text = "  " + mainViewModel.readValue(index),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                color = Color(0xff004f88)
            )
        }
    }
}

@Composable
fun SettingsButton(
    name: String,
    mainViewModel: MainViewModel,
    index: Int
) {
    Column {
        OutlinedButton(
            onClick = {
                if (name === "+")
                    mainViewModel.increaseSettingsValue(index)
                else
                    mainViewModel.decreaseSettingsValue(index)

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
                fontSize = 30.sp
            )
        }
    }
}