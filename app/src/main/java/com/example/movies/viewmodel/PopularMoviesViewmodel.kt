package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.Movie
import com.example.movies.repository.PopularMoviesRepository

class PopularMoviesViewmodel(private val moviesRepository: PopularMoviesRepository) : ViewModel() {

    private val _popularMovieList = MutableLiveData<List<Movie>?>()
    val popularMovieList: MutableLiveData<List<Movie>?> get() = _popularMovieList

    private val _errorHandle = MutableLiveData<String?>()
    val errorHandle: LiveData<String?> get() = _errorHandle

    fun fetchPopularMovies() {
        moviesRepository.getMovies { moviesList, error ->
            if (moviesList != null) {
                _popularMovieList.postValue(moviesList)
            } else {
                _errorHandle.postValue(error)
            }
        }
    }
}
