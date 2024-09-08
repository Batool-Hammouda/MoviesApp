package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.repository.PopularMoviesRepository

@Suppress("UNCHECKED_CAST")
class PopularViewmodelFactory(private val repo:PopularMoviesRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PopularMoviesViewmodel::class.java)) {
            return PopularMoviesViewmodel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}