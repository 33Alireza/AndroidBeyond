package com.example.androidbeyond.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val moviesList = mutableListOf(
        "Interstellar",
        "TENET",
        "oppenheimer",
        "The Odyssey",
        "Pulp Fiction",
        "Django Unchained",
        "Inglourious Basterds",
        "The Hateful Eight",
        "Casino",
        "Goodfellas",
        "The Irishman",
        "killers of the Flower Moon",
        "Arrival",
        "Prisoners",
        "Dune",
        "The Last Duel",
        "House of Gucci",
        "The Martian",
        "Napoleon",
        "Snatch",
        "Rush",
        "Another Round"
    )
    val colorsList = mutableListOf(
        Color(0xFFE91E63),
        Color(0xFFF44336),
        Color(0xFF9C27B0),
        Color(0xFF673AB7),
        Color(0xFF3F51B5),
        Color(0xFF2196F3),
        Color(0xFF03A9F4),
        Color(0xFF00BCD4),
        Color(0xFF009688),
        Color(0xFF4CAF50),
        Color(0xFF8BC34A),
        Color(0xFFCDDC39),
        Color(0xFFFFEB3B),
        Color(0xFFFFC107),
        Color(0xFFFF9800),
        Color(0xFFFF5722),
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        LazyColumn {
            items(moviesList) { movie ->
                Card(
                    modifier = Modifier, colors = CardDefaults.cardColors(
                        containerColor = colorsList.random()
                    )
                ) {
                    Text(
                        text = movie, fontWeight = FontWeight.ExtraBold, fontSize = 30.sp
                    )
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}