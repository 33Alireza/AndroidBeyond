package com.example.androidbeyond.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidbeyond.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()
) {
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}