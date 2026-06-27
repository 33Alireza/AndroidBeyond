package com.example.androidbeyond.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {

    var backgroundColor = MutableStateFlow(Color.White)
        private set

    private val colors = mutableStateListOf(
        Color(0xFFE91E63),
        Color(0xFFF44336),
        Color(0xFF9C27B0),
        Color(0xFF673AB7),
        Color(0xFF3F51B5),
        Color(0xFF2196F3),
        Color(0xFF03A9F4),
        Color(0xFF00BCD4),
        Color(0xFF009688),
        Color(0xFF4CAF50),
        Color(0xFF8BC34A),
        Color(0xFFCDDC39),
        Color(0xFFFFEB3B),
        Color(0xFFFFC107),
        Color(0xFFFF9800),
        Color(0xFFFF5722),
    )

    fun changeBackground() {
        backgroundColor.value = colors.random()
    }

}