package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {
    private val _me = MutableStateFlow<Me?>(null)
    val me = _me.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _event = MutableSharedFlow<String>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val event = _event.asSharedFlow()

    private var meJob: Job? = null

    fun loadMe() {
        meJob?.cancel()
        _me.value = null
        meJob = viewModelScope.launch {
            _isLoading.value = true
            try {
                coroutineScope {
                    val deferredMovies = async { fetchMovies() }
                    val deferredDev = async { fetchDev() }
                    val deferredMusics = async { fetchMusics() }
                    val deferredGames = async { fetchGames() }
                    val deferredTech = async { fetchTech() }

                    val movies = deferredMovies.await()
                    val dev = deferredDev.await()
                    val musics = deferredMusics.await()
                    val games = deferredGames.await()
                    val tech = deferredTech.await()

                    _me.value = Me(movies, dev, musics, games, tech)

                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _event.tryEmit(e.message ?: "Unknown Error")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun fetchMovies(): List<Movie> = withContext(Dispatchers.IO) {
        delay(1500.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Server Error")
        } else {
            listOf(
                Movie("Interstellar", "2014"),
                Movie("Pulp Fiction", "1994"),
                Movie("Arrival", "2015"),
                Movie("Casino", "1993"),
                Movie("Snatch", "2000"),
            )
        }
    }

    private suspend fun fetchDev(): List<Dev> = withContext(Dispatchers.IO) {
        delay(1500.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Server Error")
        } else {
            listOf(
                Dev("Kotlin"),
                Dev("Jetpack Compose"),
                Dev("JavaScript"),
                Dev("TypeScript"),
                Dev("Next.js"),
            )
        }
    }

    private suspend fun fetchMusics(): List<Music> = withContext(Dispatchers.IO) {
        delay(1500.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Server Error")
        } else {
            listOf(
                Music("Master of Puppets", "Metallica"),
                Music("Paranoid", "Black Sabbath"),
                Music("Beautiful", "Eminem"),
                Music("Smells Like Teen Spirit", "Nirvana"),
                Music("The Chain", "Fleetwood Mac"),
            )
        }
    }

    private suspend fun fetchGames(): List<Game> = withContext(Dispatchers.IO) {
        delay(1500.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Server Error")
        } else {
            listOf(
                Game("Red Dead Redemption 2"),
                Game("Grand Theft Auto V"),
                Game("Battlefield"),
                Game("Assassin's Creed Valhalla"),
                Game("The Witcher Wild Hunt"),
            )
        }
    }

    private suspend fun fetchTech(): List<Tech> = withContext(Dispatchers.IO) {
        delay(1500.milliseconds)
        if ((1..100).random() > 80) {
            throw Exception("Server Error")
        } else {
            listOf(
                Tech("MacBook Pro"),
                Tech("ASUS Rog Strix"),
                Tech("AirPods Max"),
                Tech("iPhone"),
                Tech("Pixel"),
            )
        }
    }
}

data class Me(
    val movies: List<Movie>,
    val dev: List<Dev>,
    val musics: List<Music>,
    val games: List<Game>,
    val tech: List<Tech>,
)

data class Movie(
    val name: String,
    val year: String,
)

data class Dev(
    val name: String,
)

data class Music(
    val name: String,
    val singerOrBand: String,
)

data class Game(
    val name: String,
)

data class Tech(
    val name: String,
)