package com.example.androidbeyond.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidbeyond.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
) {
    val flowBackgroundColor = homeViewModel.flowBackgroundColor.collectAsStateWithLifecycle().value
    val composeBackgroundColor = homeViewModel.composeBackgroundColor

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(flowBackgroundColor)
            .clickable(onClick = { homeViewModel.changeColor() })
    ) { }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}