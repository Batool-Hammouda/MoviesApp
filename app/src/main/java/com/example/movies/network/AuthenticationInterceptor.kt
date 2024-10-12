package com.example.movies.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0M2Y0ZmI1N2NhOGIyM2Q2ZTMwOTQwODJmYTMwNzU4ZiIsIm5iZiI6MTcyNjIzMTM5Ni43NTA0MzEsInN1YiI6IjY2ZDgxNTQ4YTBhOGYzNWIxODI2ZTk1ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rS-OcpaLHYC2JgS5hO5vmOzozkNojNPA1G-S-9SRrvo")
            .build()

        Log.d("AuthenticationInterceptor", "Interceptor added Bearer token")
        return chain.proceed(request)
    }
}