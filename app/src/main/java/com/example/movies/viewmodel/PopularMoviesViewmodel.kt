package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movies.model.Movie
import com.example.movies.repository.PopularMoviesRepository

class PopularMoviesViewmodel(private val moviesRepository: PopularMoviesRepository) : ViewModel() {

    fun fetchPopularMovies(callback: (List<Movie>?, String?) -> Unit) {
        moviesRepository.getMovies { moviesList, error ->
            if (moviesList != null) {
                callback(moviesList, null)
            } else {
                callback(null, error)
            }
        }
    }
}