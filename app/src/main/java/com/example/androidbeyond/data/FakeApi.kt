package com.example.androidbeyond.data

import com.example.androidbeyond.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

object FakeApi {
    private val movies = listOf(
        Movie(0, "Interstellar", "2014"),
        Movie(1, "Pulp Fiction", "1994"),
        Movie(2, "Casino", "1991"),
        Movie(3, "Arrival", "2015"),
        Movie(4, "Snatch", "2000"),
        Movie(5, "Rush", "2013"),
        Movie(6, "Birdman or The Unexpected Virtue of Ignorance", "2014"),
    )

    suspend fun getMovies(): List<Movie> = withContext(Dispatchers.IO) {
        delay(5000.milliseconds)
        if ((1..100).random() < 30) {
            throw Exception("Ho ho ho!")
        } else {
            movies
        }
    }

    suspend fun getMovie(id: Int): Movie = withContext(Dispatchers.IO) {
        delay(3000.milliseconds)
        if ((1..100).random() < 20) {
            throw Exception("Yaa!")
        } else {
            movies.find { it.id == id } ?: Movie(0, "not found", "nope")
        }
    }
}