package com.example.androidbeyond.view

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.androidbeyond.navigation.Detail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _number = MutableStateFlow("")
    val number = _number.asStateFlow()

    private val savedNumber = savedStateHandle.toRoute<Detail>().id

    init {
        _number.value = "number is $savedNumber"
    }
}