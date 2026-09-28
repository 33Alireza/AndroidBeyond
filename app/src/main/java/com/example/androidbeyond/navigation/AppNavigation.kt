package com.example.androidbeyond.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidbeyond.view.DetailScreen
import com.example.androidbeyond.view.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> { HomeScreen() }
        composable<Detail> { DetailScreen() }
    }
}