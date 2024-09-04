package com.example.movies

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Authentication Key","eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyZTYyZTc1ODEwOWRhNjJiNDRmNDdkNzhmYWJjMjFiYyIsIm5iZiI6MTcyNTYyMTk5NC43NzQzNTgsInN1YiI6IjY2ZDgxNTQ4YTBhOGYzNWIxODI2ZTk1ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.aU6-M7R3tGrgox9cCWOwmxa_ktfypLBilj3FZzu0fRY")
            .build()
        return chain.proceed(request)
    }
}