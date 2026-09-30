package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _numbersList = MutableStateFlow(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val numbersList = _numbersList.asStateFlow()
}