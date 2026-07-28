package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _product = MutableStateFlow<ProductDetails?>(null)
    val product = _product.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _event = MutableSharedFlow<String>()
    val event = _event.asSharedFlow()

    private val job: Job? = null
}

data class ProductDetails(
    val info: ProductInfo,
    val reviews: List<String>,
    val relatedProducts: List<String>
)

data class ProductInfo(
    val name: String,
    val price: Double,
    val description: String
)