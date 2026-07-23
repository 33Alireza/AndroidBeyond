package com.example.androidbeyond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidbeyond.ui.theme.AndroidBeyondTheme
import com.example.androidbeyond.view.HomeScreen
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AndroidBeyondTheme {
                HomeScreen()
            }
        }
        println("Start")
        thread {
            println("Thread start")
            blockingCode()
            println("Thread end")
        }
        println("End")
    }
}

fun blockingCode() {
    (1..50_000_000).forEach { it * it }
    println("Blocking code finished")
}