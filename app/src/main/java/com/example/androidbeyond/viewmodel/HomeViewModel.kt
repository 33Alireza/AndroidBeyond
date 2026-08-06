package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private val _currentNumber = MutableStateFlow<Int?>(null)
    val currentNumber = _currentNumber.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    private val _event = MutableSharedFlow<String>()
    val event = _event.asSharedFlow()

    private var job: Job? = null

    fun startCounting() {
        job?.cancel()
        _isRunning.value = true
        job = viewModelScope.launch {
            try {
                createNumberFlow().collect { number ->
                    _currentNumber.value = number
                }
            } catch (e: Exception) {
                _event.emit(e.message ?: "Unknown Error")
            } finally {
                _isRunning.value = false
            }
        }
    }

    fun stopCounting() {
        job?.cancel()
        job = null
        _currentNumber.value = null
        _isRunning.value = false
    }

    private fun createNumberFlow(): Flow<Int> {
        return flow {
            for (i in 1..5){
                emit(i)
                delay(1000.milliseconds)
            }
        }
    }
}