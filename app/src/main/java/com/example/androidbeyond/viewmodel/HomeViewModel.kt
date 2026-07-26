package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
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

    fun loadDashboardDate() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                launch {
                    withContext(Dispatchers.IO) {
                        val profileDeferred = async { fetchUserProfile() }
                        val profile = profileDeferred.await()
                        _dashboardData.value?.userName = profile
                    }
                }
                launch {
                    withContext(Dispatchers.IO) {
                        val ordersDeferred = async { fetchRecentOrders() }
                        val orders = ordersDeferred.await()
                        _dashboardData.value?.orderCount = orders
                    }
                }
            } catch (e: Exception) {
                println(e.message ?: "Unknown Error")
            } finally {
                _isLoading.value = false
            }
        }
    }
}

data class DashboardData(
    var userName: String,
    var orderCount: Int,
)

private suspend fun fetchUserProfile(): String {
    delay(1500.milliseconds)
    return "Alireza"
}

private suspend fun fetchRecentOrders(): Int {
    delay(2000.milliseconds)
    return 7
}