package com.example.movies.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val auth_token = OkHttpClient.Builder()
        .addInterceptor(AuthenticationInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(auth_token)
        .build()

    val api: ApiService = retrofit.create(ApiService::class.java)
}
