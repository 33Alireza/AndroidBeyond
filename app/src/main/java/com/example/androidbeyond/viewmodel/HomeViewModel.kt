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
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile = _userProfile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val job: Job? = null

    fun loadUserProfile() {
        job?.cancel()
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val deferredBasinInfo = async { fetchBasicInfo() }
                val deferredUserStats = async { fetchUserStats() }
                val deferredLastLogin = async { fetchLastLogin() }

                val basicInfo = deferredBasinInfo.await()
                val userStats = deferredUserStats.await()
                val lastLogin = deferredLastLogin.await()

                _userProfile.value = UserProfile(
                    name = basicInfo.name,
                    email = basicInfo.email,
                    posts = userStats.posts,
                    followers = userStats.followers,
                    lastLogin = lastLogin
                )
            } catch (e: Exception) {
                println(e.message ?: "Unknown Error")
            } finally {
                _isLoading.value = false
            }
        }

    }

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