package com.example.movies.network

import com.example.movies.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/popular")
   fun getMovies(@Query("language") language:String,
                  @Query("page")page:Int
    ): Call<Response>
}