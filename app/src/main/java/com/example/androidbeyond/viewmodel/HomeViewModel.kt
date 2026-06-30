package com.example.androidbeyond.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var backgroundColor = Color.White
        private set

    private val colorsList = mutableListOf(
        Color(0xFFE91E63),
        Color(0xFF2196F3),
        Color(0xFFFF9800),
        Color(0xFF4CAF50),
    )

    fun changeColor() {
        backgroundColor = colorsList.random()
    }
}