package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private var _counter = MutableStateFlow(0.0)
    val counter = _counter.asStateFlow()

    private var job: Job? = null

    private val _lapsList = MutableStateFlow<List<Double>>(emptyList())
    val lapsList = _lapsList.asStateFlow()

    fun startCounting() {
        if (job?.isActive == true) return
        job = viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {
                delay(1000.milliseconds)
                _counter.value += 0.1
            }
        }
    }

    fun stopCounting() {
        viewModelScope.launch {
            job?.cancel()
            job = null
        }
    }

    fun resetCounter() {
        _counter.value = 0.0
    }

    fun initLap(lap: Double) {
        _lapsList.value += lap
    }
}