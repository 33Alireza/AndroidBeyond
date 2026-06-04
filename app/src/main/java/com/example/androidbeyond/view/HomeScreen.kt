package com.example.androidbeyond.view

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Button(
        modifier = modifier.clip(RoundedCornerShape(15.dp)), colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE91E63),
            contentColor = Color.Black,
        ), onClick = {}) {
        Text(
            text = "Kotlin",
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}