package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

}

data class ProductInfo(
    val name: String,
    val price: Double,
    val description: String
)