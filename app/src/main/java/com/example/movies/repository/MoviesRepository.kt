package com.example.movies.repository

import com.example.movies.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
   fun getMovies(): Flow<List<Movie>?>
}
