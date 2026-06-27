package com.example.androidbeyond.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {

}

data class Music(
    val id: String, val name: String, val bandOrSinger: String
)

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}