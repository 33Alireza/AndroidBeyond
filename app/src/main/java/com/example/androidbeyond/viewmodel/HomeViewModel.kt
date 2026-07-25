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
    private var _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    private var job: Job? = null

    fun startCounting() {
        if (job?.isActive == true) return
        job = viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {
                delay(1000L.milliseconds)
                _counter.value += 1
            }
        }
    }

    fun pauseCounting() {
        viewModelScope.launch {
            job?.cancel()
            job = null
        }
    }
}