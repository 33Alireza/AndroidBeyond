package com.example.androidbeyond.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE91E63),
                    contentColor = Color.Black,
                ),
                onClick = {}) {
                Text(
                    text = "Kotlin",
                    fontWeight = FontWeight.ExtraBold,
                )
            }
            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.Black,
                ),
                onClick = {}) {
                Text(
                    text = "Android",
                    fontWeight = FontWeight.ExtraBold,
                )
            }
            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800),
                    contentColor = Color.Black,
                ),
                onClick = {}) {
                Text(
                    text = "Compose",
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    contentColor = Color.Black,
                ),
                onClick = {}) {
                Text(
                    text = "Retrofit",
                    fontWeight = FontWeight.ExtraBold,
                )
            }

            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9C27B0),
                    contentColor = Color.Black,
                ),
                onClick = {}) {
                Text(
                    text = "Ktor",
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}