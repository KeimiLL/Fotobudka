package com.mobilne.foto_zabawa.ui.main.views.camera

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.foto_zabawa.ui.main.MainViewModel

@Composable
fun CapturePictureButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    mainViewModel: MainViewModel
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) Color.Blue else Color.Black
    val contentPadding = PaddingValues(if (isPressed) 8.dp else 12.dp)
    OutlinedButton(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(2.dp, Color.Black),
        contentPadding = contentPadding,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        onClick = { },
        enabled = false
    ) {
        Button(
            modifier = Modifier
                .fillMaxSize(),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = color
            ),
            interactionSource = interactionSource,
            onClick = onClick,
            enabled = mainViewModel.isButtonEnable
        ) {
            // No content
        }
    }
}

//@Preview
//@Composable
//fun PreviewCapturePictureButton() {
//    Scaffold(
//        modifier = Modifier
//            .size(125.dp)
//            .wrapContentSize()
//    ) { innerPadding ->
//        CapturePictureButton(
//            modifier = Modifier
//                .padding(innerPadding)
//                .size(100.dp)
//
//        )
//    }
//}