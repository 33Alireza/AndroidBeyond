package com.example.androidbeyond.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidbeyond.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()
) {
    val counter = viewModel.counter.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { message ->
            println("Event received: $message")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.message.collect { message ->
            println("Message: $message")
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(counter.value.toString())
        Spacer(Modifier.height(24.dp))
        Button(onClick = { viewModel.sendEvent("button clicked!") }) {
            Text("Send Event")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}