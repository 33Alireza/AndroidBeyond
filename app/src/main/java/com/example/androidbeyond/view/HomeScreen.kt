package com.example.androidbeyond.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
    val dashboardData = viewModel.dashboardData.collectAsStateWithLifecycle()
    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.loadDashboardData() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading.value) {
            CircularProgressIndicator()
        } else {
            dashboardData.value?.let {
                Text(it.userName)
                Text(it.orderCount.toString())
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}