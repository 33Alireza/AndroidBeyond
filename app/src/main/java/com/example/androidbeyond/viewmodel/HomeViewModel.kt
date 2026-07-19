package com.example.androidbeyond.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigInteger

class HomeViewModel : ViewModel() {
    var counterValue: BigInteger by mutableStateOf(BigInteger.ONE)
        private set

    fun initialCounter() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                counterValue += counterValue
            }
        }
    }
}