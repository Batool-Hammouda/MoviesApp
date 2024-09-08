package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movies.repository.PopularMoviesRepository
import com.example.movies.model.Response

class PopularMoviesViewmodel(private val moviesRepository : PopularMoviesRepository):ViewModel()  {

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