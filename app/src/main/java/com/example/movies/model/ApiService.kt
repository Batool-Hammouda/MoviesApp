package com.example.movies.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/popular")
    fun getMovies(@Query("language") language:String,
                  @Query("1")page:Int
                  ): Call<List<Response>>
}