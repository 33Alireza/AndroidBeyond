package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private var _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    fun increaseNumber() {
        _number.value += 1
    }
}