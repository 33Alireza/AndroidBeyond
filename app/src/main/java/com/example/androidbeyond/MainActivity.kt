package com.example.androidbeyond

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidbeyond.ui.theme.AndroidBeyondTheme
import com.example.androidbeyond.view.HomeScreen
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("onCreate()")
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        val uri = "android.resource://$packageName/drawable/ic_launcher_background.xml".toUri()
        val launcherBytes = contentResolver.openInputStream(uri)?.use { it.readBytes() }
        println(launcherBytes.toString())

        val file = File(filesDir, "ic_launcher_background.xml")
        FileOutputStream(file).use {
            it.write(launcherBytes)
        }
        println(file.toUri())

        val dataUri = "data:text/plain;charset=UTF-8,Hello%20World".toUri()

        setContent {
            AndroidBeyondTheme {
                val pickImage = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { contentUri ->
                        println(contentUri)
                    }
                )
                HomeScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("onStart()")
    }

    override fun onResume() {
        super.onResume()
        println("onResume()")
    }

    override fun onPause() {
        super.onPause()
        println("onPause()")
    }

    override fun onStop() {
        super.onStop()
        println("onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy()")
    }
}