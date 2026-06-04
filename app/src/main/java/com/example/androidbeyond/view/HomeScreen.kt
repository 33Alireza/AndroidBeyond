package com.example.androidbeyond.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Android",
        color = Color.Cyan,
        fontWeight = FontWeight.ExtraBold
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}