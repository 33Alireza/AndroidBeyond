package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private fun createQueryFlow(): Flow<String> = flow {
        listOf("K", "Ko", "Kot", "Kotl", "Kotlin").forEach { query ->
            emit(query)
            delay(600.milliseconds)
        }
    }

    private suspend fun search(query: String): String {
        delay(1000.milliseconds)
        return "Result for: $query"
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResult: StateFlow<String?> = createQueryFlow()
        .flatMapLatest { query ->
            flow {
                emit(search(query))
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}