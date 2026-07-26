package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private val _dashboardData = MutableStateFlow<DashboardData?>(null)
    val dashboardData = _dashboardData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var job: Job? = null

    fun loadDashboardData() {
        job?.cancel()
        job = viewModelScope.launch {
            _isLoading.value = true
            try {
                val profileDeferred = async { fetchUserProfile() }
                val ordersDeferred = async { fetchRecentOrders() }

                val profile = profileDeferred.await()
                val orders = ordersDeferred.await()

                _dashboardData.value = DashboardData(profile, orders)
            } catch (e: Exception) {
                println(e.message ?: "Unknown Error")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun fetchUserProfile(): String = withContext(Dispatchers.IO) {
        delay(1500.milliseconds)
        "Alireza"
    }

    private suspend fun fetchRecentOrders(): Int = withContext(Dispatchers.IO) {
        delay(2000.milliseconds)
        7
    }
}

data class DashboardData(
    val userName: String,
    val orderCount: Int,
)