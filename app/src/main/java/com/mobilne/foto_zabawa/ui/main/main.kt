package com.mobilne.foto_zabawa.ui.main

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.foto_zabawa.R


@Composable
fun mainView() {
    Box(
        modifier = Modifier
            .background(Color(0xFFc5ddf6))
            .fillMaxSize()
    ) {Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xffcccccc))
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        NavigationButton(R.drawable.settings, "ustawienia")
        NavigationButton(R.drawable.camera, "aparat")
        NavigationButton(R.drawable.gallery, "galeria")
    }

    }
}

@Composable
fun NavigationButton(
    icon: Int,
    name: String
){
    // Fetching the local context
    val mContext = LocalContext.current
    Column() {
        OutlinedButton(onClick = { Toast.makeText(mContext, "Idziesz do ${name}", Toast.LENGTH_LONG).show()},
            modifier= Modifier.size(60.dp),
            shape = CircleShape,
            border= BorderStroke(5.dp, Color(0xff004f88)),
            contentPadding = PaddingValues(1.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Blue)
        ) {
            // Adding an Icon "Add" inside the Button
            Image(painterResource(id = icon) , contentDescription = name)
//            Icon( ,contentDescription = "content description", tint=Color(0xff004f88))
        }

    }
}




@Preview(showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun DefaultPreview() {
    mainView()
}