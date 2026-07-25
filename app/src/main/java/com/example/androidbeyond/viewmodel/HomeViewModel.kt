package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private var _counter = MutableStateFlow(0L)
    val counter = _counter.asStateFlow()

    private var job: Job? = null

    private val _lapsList = MutableStateFlow<List<Long>>(emptyList())
    val lapsList = _lapsList.asStateFlow()

    fun startCounting() {
        if (job?.isActive == true) return
        job = viewModelScope.launch {
            while (isActive) {
                delay(100.milliseconds)
                _counter.value += 100
            }
        }
    }

    fun stopCounting() {
        job?.cancel()
        job = null
    }

    fun resetCounter() {
        stopCounting()
        _counter.value = 0L
        _lapsList.value = emptyList()
    }

    fun initLap(lap: Long) {
        _lapsList.value += lap
    }
}