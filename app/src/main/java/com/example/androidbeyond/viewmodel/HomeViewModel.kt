package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
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

    private fun collectFlow() {
        val flow = flow {
            delay(250.milliseconds)
            emit("Appetizer")
            delay(1000.milliseconds)
            emit("Main Dish")
            delay(100.milliseconds)
            emit("Desert")
        }
        viewModelScope.launch {
            flow.onEach {
                println("FLOW: $it is delivered")
            }
                .conflate()
                .collect {
                    println("FLOW: Now eating $it")
                    delay(1500.milliseconds)
                    println("FLOW: Finished eating $it")
                }
        }
    }
}