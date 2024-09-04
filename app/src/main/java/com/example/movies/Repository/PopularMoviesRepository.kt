package com.example.movies.Repository

import com.example.movies.RetrofitInstance
import com.example.movies.model.Response
import retrofit2.Call

class PopularMoviesRepository: MoviesRepository {
    private val apiService = RetrofitInstance.api

   override fun getMovies(callback: (List<Response>?, String?) -> Unit) {
        val call = apiService.getMovies()
       call.enqueue(object :retrofit2.Callback<List<Response>>{
           override fun onResponse(
               call: Call<List<Response>>,
               response: retrofit2.Response<List<Response>>
           ) {
               when {
                   response.isSuccessful -> {
                       callback(response.body(), null)
                   }
                   response.code() == 400 -> {
                       callback(null, "Bad Request: Check your input or request format.")
                   }
                   response.code() == 404 -> {
                       callback(null, "Not Found: The resource you are looking for could not be found.")
                   }
                   response.code() == 500 -> {
                       callback(null, "Internal Server Error: Something went wrong on the server side.")
                   }
                   else -> {
                       callback(null, "Error: ${response.code()}")
                   }
               }
           }

           override fun onFailure(call: Call<List<Response>>, t: Throwable) {
               callback(null, t.message)
           }
      })
    }
}