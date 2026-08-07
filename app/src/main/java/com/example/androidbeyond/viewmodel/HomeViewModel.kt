package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    val currentNumber: StateFlow<Int?> = createNumberFlow()
        .filter { it % 2 == 0 }
        .map { it * 10 }
        .distinctUntilChanged()
        .onEach { println(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private fun createNumberFlow(): Flow<Int> {
        return flow {
            for (i in 1..10) {
                emit(i)
                delay(400.milliseconds)
            }
        }
    }
}