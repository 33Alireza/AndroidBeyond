package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    val numberFlow = flow {
        delay(1000.milliseconds)
        emit(1)
        delay(1000.milliseconds)
        emit(2)
        delay(1000.milliseconds)
        emit(3)
        delay(1000.milliseconds)
        emit(4)
        delay(1000.milliseconds)
        emit(5)
    }
}