package com.example.movies.model

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movies")
    fun getMovies(): Call<List<Response>>
}