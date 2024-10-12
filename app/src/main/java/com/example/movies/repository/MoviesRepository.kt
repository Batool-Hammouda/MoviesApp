package com.example.movies.repository

import com.example.movies.model.Movie

interface MoviesRepository {
    fun getMovies(callback: (List<Movie>?, String?) -> Unit)
}