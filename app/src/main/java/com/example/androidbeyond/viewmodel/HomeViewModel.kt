package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    // correct version
    private val _numberOne = MutableStateFlow(3)
    val numberOne = _numberOne.asStateFlow()

    // wrong version
    var numberTwo = MutableStateFlow(4)
        private set
}