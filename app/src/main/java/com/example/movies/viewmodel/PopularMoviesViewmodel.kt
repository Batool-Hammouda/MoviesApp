package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.Movie
import com.example.movies.network.HTTPErrorsException
import com.example.movies.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class PopularMoviesViewmodel(private val moviesRepository: PopularMoviesRepository) : ViewModel() {

    private val _popularMoviesFlow = MutableSharedFlow<List<Movie>?>()
    val popularMoviesFlow: SharedFlow<List<Movie>?> get() = _popularMoviesFlow


    fun fetchMovies() {
        viewModelScope.launch {
            try {
                moviesRepository.getMovies().collect { moviesList ->
                    _popularMoviesFlow.emit(moviesList)
                }
            } catch (e: HTTPErrorsException) {
                when (e) {
                    is HTTPErrorsException.UnknownErrorException -> _popularMoviesFlow.emit(
                        emptyList()
                    )

                    is HTTPErrorsException.UnauthorizedException -> _popularMoviesFlow.emit(
                        emptyList()
                    )

                    is HTTPErrorsException.NotFoundException -> _popularMoviesFlow.emit(emptyList())
                }
            }

        }
    }
}
