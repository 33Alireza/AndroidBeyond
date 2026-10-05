package com.example.androidbeyond.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.androidbeyond.data.FakeApi
import com.example.androidbeyond.model.Movie
import com.example.androidbeyond.navigation.Detail
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _movie = MutableStateFlow<Movie?>(null)
    val movie = _movie.asStateFlow()

    var isLoading = MutableStateFlow(false)
        private set

    private val _event = MutableSharedFlow<String>()
    val event = _event.asSharedFlow()

    private val savedId = savedStateHandle.toRoute<Detail>().id

    var job: Job? = null

    init {
        getMovie()
    }

    fun getMovie() {
        job?.cancel()
        job = viewModelScope.launch {
            try {
                isLoading.value = true
                val movie = FakeApi.getMovie(savedId)
                _movie.update { movie }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _event.emit(e.message ?: "loo!")
            } finally {
                isLoading.value = false
            }
        }
    }
}