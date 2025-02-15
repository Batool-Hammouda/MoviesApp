package com.example.movies.repository

import com.example.movies.model.Movie
<<<<<<< HEAD
import kotlinx.coroutines.flow.Flow
=======
>>>>>>> origin/development

interface MoviesRepository {
   fun getMovies(): Flow<List<Movie>?>
}