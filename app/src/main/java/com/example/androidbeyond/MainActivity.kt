package com.example.androidbeyond

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidbeyond.ui.theme.AndroidBeyondTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AndroidBeyondTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            /*Intent(applicationContext, SecondActivity::class.java).also {
                                startActivity(it)
                            }*/
                            /*Intent(Intent.ACTION_MAIN).also {
                                it.`package` = "com.google.android.youtube"
                                try {
                                    startActivity(it)
                                } catch (e: ActivityNotFoundException) {
                                    e.printStackTrace()
                                }
                            }*/
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_EMAIL, arrayOf("test@test.com"))
                                putExtra(Intent.EXTRA_SUBJECT, arrayOf("Subject"))
                                putExtra(Intent.EXTRA_TEXT, arrayOf("Yo!"))
                            }
                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            }
                        }) {
                        Text("Click Me")
                    }
                }
            }
        }
    }
}