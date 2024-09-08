package com.example.movies

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url.newBuilder()
            .addQueryParameter("Authorization", "Bearer 2e62e758109da62b44f47d78fabc21bc").build()
        val request = chain.request().newBuilder().url(url).build()

        return chain.proceed(request)
    }
}