package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    val result: StateFlow<String?> = createNumbersFlow()
        .map { it.toString() }
        .retry(2)
        .catch { e ->
            emit("Error: ${e.message}")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private fun createNumbersFlow() = flow {
        for (i in 1..5) {
            if (i == 3) throw Exception("FLOW Exception")
            else emit(i)
            delay(1000.milliseconds)
        }
    }
}