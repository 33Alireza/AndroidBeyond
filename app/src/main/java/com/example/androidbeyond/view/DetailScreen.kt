package com.example.androidbeyond.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidbeyond.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    navigateToPreviousScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel()
) {
    val movie by viewModel.movie.collectAsStateWithLifecycle()
    val event = viewModel.event
    val snackBarState = remember { SnackbarHostState() }
    val actionLabel = "Retry"

    LaunchedEffect(movie) {
        event.collect {
            val result = snackBarState.showSnackbar(
                message = "Yo",
                actionLabel = actionLabel,
                duration = SnackbarDuration.Indefinite
            )

            if (result == SnackbarResult.ActionPerformed) viewModel.getMovie()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarState) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navigateToPreviousScreen() }
            ) {
                Text("Back")
            }
            movie?.let { movie ->

                Text(movie.id.toString())
                Text(movie.name)
                Text(movie.year)
            }
        }
    }
}