package com.example.androidbeyond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.androidbeyond.ui.theme.AndroidBeyondTheme
import com.example.androidbeyond.view.HomeScreen

class MainActivity : ComponentActivity(), AnalyticsLogger by AnalyticsLoggerImpl() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLifecycleOwner(this)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AndroidBeyondTheme {
                HomeScreen()
            }
        }
    }
}

interface AnalyticsLogger {
    fun registerLifecycleOwner(owner: LifecycleOwner)
}

class AnalyticsLoggerImpl : AnalyticsLogger, LifecycleEventObserver {

    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> println("User opened the screen")
            Lifecycle.Event.ON_PAUSE -> println("User leaved the screen")
            else -> Unit
        }
    }
}