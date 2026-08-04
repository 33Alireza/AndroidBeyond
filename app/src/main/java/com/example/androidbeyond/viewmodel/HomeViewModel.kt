package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private val counterFlow = flow {
        var count = 0
        while (true) {
            emit(count++)
            delay(1000.milliseconds)
        }
    }

    val counter: StateFlow<Int> = counterFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    val message: SharedFlow<String> = flow {
        emit("Hello")
        delay(2000.milliseconds)
        emit("World")
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        replay = 0
    )

    fun sendEvent(message: String) {
        viewModelScope.launch {
            _events.emit(message)
        }
    }
}