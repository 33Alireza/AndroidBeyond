package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private suspend fun fetchBasicInfo(): BasicInfo = withContext(Dispatchers.IO) {
        delay(1200.milliseconds)
        BasicInfo("Alireza", "alireza@gmail.com")
    }

    private suspend fun fetchUserStats(): UserStats = withContext(Dispatchers.IO) {
        delay(1800.milliseconds)
        UserStats(7, 98)
    }

    private suspend fun fetchLastLogin(): String = withContext(Dispatchers.IO) {
        delay(800.milliseconds)
        "2 hours ago"
    }
}

data class BasicInfo(
    val name: String,
    val email: String
)

data class UserStats(
    val posts: Int,
    val followers: Int
)

data class UserProfile(
    val name: String,
    val email: String,
    val posts: Int,
    val followers: Int,
    val lastLogin: String,
)