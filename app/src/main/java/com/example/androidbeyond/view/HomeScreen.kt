package com.example.androidbeyond.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidbeyond.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                try {
                    (homeViewModel.numberOne as MutableStateFlow).value = 6
                    println("number one casted successfully")
                } catch (_: Exception) {
                    println("number one was not able to cast")
                }
                try {
                    (homeViewModel.numberTwo as MutableStateFlow).value = 8
                    println("number two casted successfully")
                } catch (_: Exception) {
                    println("number two was not able to cast")
                }
            }) {
            Text("Yo!")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}