package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private val _product = MutableStateFlow<ProductDetails?>(null)
    val product = _product.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _event = MutableSharedFlow<String>()
    val event = _event.asSharedFlow()

    private val job: Job? = null

    private suspend fun fetchProductInfo(): ProductInfo = withContext(Dispatchers.IO) {
        delay(1000.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Yo!")
        } else {
            ProductInfo("MacBook Pro M5", 2.000, "16GB, 512GB, 14Inch")
        }
    }

    private suspend fun fetchReviews(): List<String> = withContext(Dispatchers.IO) {
        delay(15000.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Yo!")
        } else {
            listOf("Best Laptop ever", "Apple's finest")
        }
    }

    private suspend fun fetchRelatedProducts(): List<String> = withContext(Dispatchers.IO) {
        delay(1200.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Yo!")
        } else {
            listOf("MacBook Air M5", "MAcBook Neo")
        }
    }
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