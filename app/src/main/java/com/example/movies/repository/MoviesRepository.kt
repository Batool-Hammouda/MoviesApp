package com.example.movies.repository

import com.example.movies.model.Movie
import com.example.movies.model.Response
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(callback: (List<Movie>?, String?) -> Unit)
}