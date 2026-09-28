package com.example.androidbeyond.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes

@Serializable
data object Home : AppRoutes

@Serializable
data class Detail(
    val id: Int
) : AppRoutes