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
}

data class BasicInfo(
    val name: String,
    val email: String
)

data class UserProfile(
    val name: String,
    val email: String,
    val posts: Int,
    val followers: Int,
    val lastLogin: String,
)