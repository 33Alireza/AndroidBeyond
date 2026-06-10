package com.example.androidbeyond.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    var selectedMusician by rememberSaveable { mutableStateOf<String?>(null) }

    val musicList = remember {
        mutableListOf(
            Music("1", "Master of Puppets", "Metallica"),
            Music("2", "Enter Sandman", "Metallica"),
            Music("3", "One", "Metallica"),
            Music("4", "Seek & Destroy", "Metallica"),
            Music("5", "Sad But true", "Metallica"),
            Music("6", "For Whom the Bell Tolls", "Metallica"),
            Music("7", "The Real Slim Shady", "Eminem"),
            Music("8", "Beautiful", "Eminem"),
            Music("9", "Superman", "Eminem"),
            Music("10", "Godzilla", "Eminem"),
            Music("11", "Rap God", "Eminem"),
            Music("12", "Houdini", "Eminem"),
            Music("13", "Paranoid", "Black Sabbath"),
            Music("14", "Iron Man", "Black Sabbath"),
            Music("15", "War Pigs", "Black Sabbath"),
            Music("16", "Hole in the Sky", "Black Sabbath"),
            Music("17", "N.I.B", "Black Sabbath"),
            Music("18", "End of the Beginning", "Black Sabbath"),
            Music("19", "Come As You Are", "Nirvana"),
            Music("20", "Smells Like Teen Spirit", "Nirvana"),
            Music("21", "Something in the Way", "Nirvana"),
            Music("22", "Heart-Shaped Box", "Nirvana"),
            Music("23", "Silver", "Nirvana"),
            Music("24", "Time", "Hans Zimmer"),
            Music("25", "Cornfield Chase", "Hans Zimmer"),
            Music("26", "Why So Serious?", "Hans Zimmer"),
            Music("27", "Supermarine", "Hans Zimmer"),
            Music("28", "Paul's Dream", "Hans Zimmer"),
            Music("29", "F1", "Hans Zimmer"),
            Music("30", "Jack Sparrow", "Hans Zimmer"),
            Music("31", "Lost But Won", "Hans Zimmer"),
            Music("32", "STAR WARS Main Theme", "John Williams"),
            Music("33", "Raiders March", "John Williams"),
            Music("34", "Hedwig's Theme", "John Williams"),
            Music("35", "Theme from Schindler's List", "John Williams"),
            Music("36", "Rainy Night in Talin", "Ludwig Goransson"),
            Music("37", "Oppenheimer", "Ludwig Goransson"),
            Music("38", "Wakanda", "Ludwig Goransson"),

            )
    }

    val musicianList = remember {
        mutableListOf(
            "All",
            "Metallica",
            "Eminem",
            "Black Sabbath",
            "Nirvana",
            "Hans Zimmer",
            "John Williams",
            "Ludwig Goransson"
        )
    }

    val colorsList = remember {
        mutableListOf(
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
    }

    val filteredList by remember(selectedMusician, musicList) {
        mutableStateOf(
            if (selectedMusician == null || selectedMusician == "All") musicList
            else musicList.filter { it.bandOrSinger == selectedMusician })
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(musicianList) { musician ->
                    val isSelected =
                        (musician == "All" && selectedMusician == null) || musician == selectedMusician
                    FilterChip(
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                        selected = isSelected,
                        onClick = {
                            selectedMusician = when (musician) {
                                "All" -> null
                                selectedMusician -> null
                                else -> musician
                            }
                        },
                        label = { Text(musician) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            selectedContainerColor = colorsList.random(),
                        ),
                        border = null
                    )
                }
            }

            LazyColumn {
                items(filteredList) { music ->
                    Card(
                        modifier = Modifier, colors = CardDefaults.cardColors(
                            containerColor = colorsList.random()
                        )
                    ) {
                        Text(
                            text = "${music.name} -> ${music.bandOrSinger}",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}

data class Music(
    val id: String, val name: String, val bandOrSinger: String
)

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}