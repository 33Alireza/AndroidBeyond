package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbeyond.data.FakeApi
import com.example.androidbeyond.model.Movie
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>?>(null)
    val movies = _movies.asStateFlow()

    var isLoading = MutableStateFlow(false)
        private set

    private val _event = MutableSharedFlow<String>()
    val event = _event.asSharedFlow()

    var job: Job? = null

    private fun getMovies() {
        job?.cancel()
        job = viewModelScope.launch {
            try {
                val referredMovies = async { FakeApi.getMovies() }
                val movies = referredMovies.await()

                _movies.update { movies }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _event.emit(e.message ?: "boom!")
            }
        }
    }
}