package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

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