package com.example.androidbeyond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidbeyond.ui.theme.AndroidBeyondTheme
import com.example.androidbeyond.view.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AndroidBeyondTheme {
                HomeScreen()
            }
        }
        val deferred = GlobalScope.async {
            delay(3000L.milliseconds)
            32
        }
        CoroutineScope(Dispatchers.Main).launch {
            val result = deferred.await()
            println(result)
        }
    }
}

suspend fun firstJob() {
    delay(3000L.milliseconds)
}

suspend fun secondJob() {
    delay(4000L.milliseconds)
}