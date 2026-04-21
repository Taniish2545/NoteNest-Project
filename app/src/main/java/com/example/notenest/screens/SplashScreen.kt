package com.example.notenest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notenest.R

@Composable
fun SplashScreen(onContinueClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.notenestlogo),
            contentDescription = "NoteNest Logo",
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "NoteNest", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Organize your thoughts easily")

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onContinueClick) {
            Text("Continue")
        }
    }
}