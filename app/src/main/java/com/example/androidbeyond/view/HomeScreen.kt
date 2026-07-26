package com.example.androidbeyond.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidbeyond.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()
) {
    val counter = viewModel.counter.collectAsStateWithLifecycle().value
    var uiState by rememberSaveable { mutableStateOf(UiState.Start) }
    val lapsList = viewModel.lapsList.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = counter.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 30.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.width(100.dp), onClick = {
                    when (uiState) {
                        UiState.Start -> {
                            viewModel.startCounting()
                            uiState = UiState.Stop
                        }

                        UiState.Stop -> {
                            viewModel.stopCounting()
                            uiState = UiState.Resume
                        }

                        UiState.Resume -> {
                            viewModel.startCounting()
                            uiState = UiState.Stop
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = when (uiState) {
                        UiState.Start -> Color(0xFF009688)
                        UiState.Stop -> Color(0xFFE91E63)
                        UiState.Resume -> Color(0xFF009688)
                    }
                )
            ) {
                Text(
                    text = when (uiState) {
                        UiState.Start -> "Start"
                        UiState.Stop -> "Stop"
                        UiState.Resume -> "Resume"
                    }
                )
            }
            Spacer(modifier.width(16.dp))
            Button(
                modifier = Modifier.width(100.dp), onClick = {
                    when (uiState) {
                        UiState.Start -> {
                            viewModel.initLap(counter)
                        }

                        UiState.Stop -> {
                            viewModel.initLap(counter)
                        }

                        UiState.Resume -> {
                            viewModel.resetCounter()
                            uiState = UiState.Start
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = when (uiState) {
                        UiState.Start -> Color(0xFF2196F3)
                        UiState.Stop -> Color(0xFF2196F3)
                        UiState.Resume -> Color(0xFFFF9800)
                    }
                )
            ) {
                Text(
                    text = when (uiState) {
                        UiState.Start -> "Lap"
                        UiState.Stop -> "Lap"
                        UiState.Resume -> "Reset"
                    }
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(lapsList.value) { lap ->
                Text(
                    text = lap.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

enum class UiState {
    Start, Stop, Resume
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}