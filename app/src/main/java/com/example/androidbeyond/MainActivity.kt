package com.example.androidbeyond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidbeyond.ui.theme.AndroidBeyondTheme
import com.example.androidbeyond.view.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.milliseconds

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
        CoroutineScope(Dispatchers.Main).launch {
            println("We're starting to cook")
            cookRice()
            println("We're done with the rice")
            cookChicken()
            println("We're done with the dish")
        }
    }
}

suspend fun cookRice(){
    delay(3000L.milliseconds)
}

suspend fun cookChicken(){
    delay(4000L.milliseconds)
}