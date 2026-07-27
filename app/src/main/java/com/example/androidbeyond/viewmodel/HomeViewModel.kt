package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile = _userProfile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _event = MutableSharedFlow<String>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val event = _event.asSharedFlow()

    private var job: Job? = null

    fun loadUserProfile() {
        job?.cancel()
        _userProfile.value = null
        job = viewModelScope.launch {
            _isLoading.value = true
            try {
                coroutineScope {
                    val deferredBasicInfo = async { fetchBasicInfo() }
                    val deferredUserStats = async { fetchUserStats() }
                    val deferredLastLogin = async { fetchLastLogin() }

                    val basicInfo = deferredBasicInfo.await()
                    val userStats = deferredUserStats.await()
                    val lastLogin = deferredLastLogin.await()

                    _userProfile.value = UserProfile(
                        name = basicInfo.name,
                        email = basicInfo.email,
                        posts = userStats.posts,
                        followers = userStats.followers,
                        lastLogin = lastLogin
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _event.tryEmit(e.message ?: "Unknown Error")
            } finally {
                _isLoading.value = false
            }
        }

    }

    private suspend fun fetchBasicInfo(): BasicInfo = withContext(Dispatchers.IO) {
        delay(1200.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Yo!")
        } else {
            BasicInfo("Alireza", "alireza@gmail.com")
        }
    }

    private suspend fun fetchUserStats(): UserStats = withContext(Dispatchers.IO) {
        delay(1800.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Yo!")
        } else {
            UserStats(7, 98)
        }
    }

    private suspend fun fetchLastLogin(): String = withContext(Dispatchers.IO) {
        delay(800.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Yo!")
        } else {
            "2 hours ago"
        }
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