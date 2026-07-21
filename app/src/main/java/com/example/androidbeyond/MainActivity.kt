package com.example.androidbeyond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidbeyond.ui.theme.AndroidBeyondTheme
import com.example.androidbeyond.view.HomeScreen
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {
    private val obj by MyLazy {
        println("Hello, World!")
        3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AndroidBeyondTheme {
                HomeScreen()
            }
        }
        println(obj)
    }
}

class MyLazy<out T : Any>(
    private var initialize: () -> T
) {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return if (value == null) {
            value = initialize()
            value!!
        } else value!!
    }
}