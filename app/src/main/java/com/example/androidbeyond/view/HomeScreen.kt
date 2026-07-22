package com.example.androidbeyond.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(100) {
                Text(
                    text = "Android",
                    fontWeight = FontWeight.Bold
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