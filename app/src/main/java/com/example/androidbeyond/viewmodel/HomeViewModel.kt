package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private var _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    fun increaseNumber() {
        _number.value += 1
    }

    init {
        viewModelScope.launch {
            println("Strat")
            firstJob()
            println("First job is done")
            secondJob()
            println("Second job is done")
            println("End")
        }
    }
}

suspend fun firstJob() {
    delay(3000L.milliseconds)
}

suspend fun secondJob() {
    delay(4000L.milliseconds)
}