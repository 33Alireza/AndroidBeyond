package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    val countDownFlow = flow {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L.milliseconds)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun collectFlow() {
        val flowOne = flow {
            emit(1)
            delay(500L.milliseconds)
            emit(2)
        }

        viewModelScope.launch {
            flowOne.flatMapMerge { value ->
                flow {
                    emit(value + 1)
                    delay(500L.milliseconds)
                    emit(value + 2)
                }
            }.collect { value ->
                println("The Value is $value")
            }
        }
    }
}