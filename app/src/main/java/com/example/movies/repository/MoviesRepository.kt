package com.example.movies.repository

import com.example.movies.model.Response

interface MoviesRepository {
    fun getMovies(callback: (List<Response>?, String?) -> Unit)
}