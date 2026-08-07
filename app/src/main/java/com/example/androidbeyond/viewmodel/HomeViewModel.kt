package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    val numberFlow: SharedFlow<Int> = createNumberFlow()
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            replay = 1
        )

    private fun createNumberFlow(): Flow<Int> {
        return flow {
            for (i in 1..5) {
                emit(i)
                delay(1000.milliseconds)
            }
        }
    }
}