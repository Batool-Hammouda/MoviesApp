package com.example.movies.repository

import com.example.movies.model.Movie
import com.example.movies.model.Response
import com.example.movies.network.HTTPErrorsException
import com.example.movies.network.HTTPStatusCode
import com.example.movies.network.RetrofitInstance
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback


class PopularMoviesRepository : MoviesRepository {
    private val apiService = RetrofitInstance.api

    override fun getMovies(): Flow<List<Movie>?> = callbackFlow {
        val call = apiService.getMovies("en-US", 1)
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                when (response.code()) {
                    HTTPStatusCode.SUCCESS.code -> {
                        val moviesList = response.body()?.movies
                        response.body()?.let { trySend(moviesList).isSuccess }
                    }

                    HTTPStatusCode.UNAUTHORIZED.code -> {
                        throw HTTPErrorsException.UnauthorizedException()
                    }

                    HTTPStatusCode.NOT_FOUND.code -> {
                        throw HTTPErrorsException.NotFoundException()
                    }

                    else -> {
                        throw HTTPErrorsException.UnknownErrorException()
                    }

                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                trySend(emptyList()).isSuccess
            }

        })

        awaitClose { call.cancel() }
    }

}