package com.example.movies.repository

import com.example.movies.model.Movie
import com.example.movies.model.Response
import com.example.movies.network.RetrofitInstance
import retrofit2.Call

class PopularMoviesRepository : MoviesRepository {
    private val apiService = RetrofitInstance.api
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
}