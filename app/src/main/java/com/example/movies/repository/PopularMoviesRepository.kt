package com.example.movies.repository

import com.example.movies.model.Movie
import com.example.movies.model.Response
import com.example.movies.network.HTTPErrorsException
import com.example.movies.network.HTTPStatusCode
import com.example.movies.network.RetrofitInstance
<<<<<<< HEAD
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
=======
>>>>>>> origin/development
import retrofit2.Call
import retrofit2.Callback


class PopularMoviesRepository : MoviesRepository {
    private val apiService = RetrofitInstance.api
<<<<<<< HEAD

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

=======
    override fun getMovies(callback: (List<Movie>?, String?) -> Unit) {
        val call: Call<Response>?
        call = apiService.getMovies("en-US", 1)

        call.enqueue(object : retrofit2.Callback<Response> {
            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                when {
                    response.isSuccessful -> {
                        val moviesList = response.body()?.movies
                        callback(moviesList, null)

                    }

                    response.code() == 400 -> {
                        callback(null, "Bad Request: Check your input or request format")
                    }

                    response.code() == 404 -> {
                        callback(
                            null,
                            "Not Found: The resource you are looking for could not be found"
                        )
                    }

                    response.code() == 500 -> {
                        callback(
                            null,
                            "Internal Server Error: Something went wrong on the server side"
                        )
                    }

                    else -> {
                        callback(null, "Error: ${response.code()}")
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                callback(null, t.message)

            }
        })

    }
>>>>>>> origin/development
}