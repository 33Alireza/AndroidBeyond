package com.example.androidbeyond.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidbeyond.viewmodel.HomeViewModel

@Composable
fun HomeScreen() {

    val homeViewModel = HomeViewModel()

    Scaffold(
        modifier = Modifier.background(homeViewModel.backgroundColor.collectAsStateWithLifecycle().value)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { homeViewModel.changeBackground() }) {
                Text("Yo")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}