package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private val _currentNumber = MutableStateFlow<Int?>(null)
    val currentNumber = _currentNumber.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    var job: Job? = null

    fun startCounting() {
        job = viewModelScope.launch {
            createNumberFlow().collect { number ->
                _currentNumber.value = number
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
            delay(1000.milliseconds)
            emit(1)
            delay(1000.milliseconds)
            emit(2)
            delay(1000.milliseconds)
            emit(3)
            delay(1000.milliseconds)
            emit(4)
            delay(1000.milliseconds)
            emit(5)
        }
    }
}