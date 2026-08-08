package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    val combined: StateFlow<String?> = combine(
        createNumbersFlow(),
        createLettersFlow()
    ) { number, letter ->
        "$number$letter"
    }
        .onEach { println(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private fun createNumbersFlow(): Flow<Int> {
        return flow {
            for (i in 1..5) {
                emit(i)
                delay(600.milliseconds)
            }
        }
    }

    private fun createLettersFlow(): Flow<String> {
        val lettersList = mutableListOf("A", "B", "C", "D", "E")
        return flow {
            for (i in lettersList) {
                emit(i)
                delay(900.milliseconds)
            }
        }
    }
}