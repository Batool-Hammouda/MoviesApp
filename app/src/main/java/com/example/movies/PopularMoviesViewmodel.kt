package com.example.movies

import com.example.movies.Repository.PopularMoviesRepository
import com.example.movies.model.Response

class PopularMoviesViewmodel {
    private val moviesRepository = PopularMoviesRepository()

    fun fetchPopularMovies(callback: (List<Response>?, String?) -> Unit) {
        moviesRepository.getMovies { moviesList, error ->
            if (moviesList != null) {
                callback(moviesList, null)
            } else {
                callback(null, error)
            }
        }
    }
}