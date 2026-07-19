package com.example.androidbeyond.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private var _backgroundColor = MutableStateFlow(Color.White)
    val backgroundColor = _backgroundColor.asStateFlow()

    private val colorsList = listOf(
        Color(0xFFE91E63),
        Color(0xFF2196F3),
        Color(0xFFFF9800),
        Color(0xFF4CAF50),
    )

    fun changeColor() {
        _backgroundColor.value = colorsList.random()
    }
}