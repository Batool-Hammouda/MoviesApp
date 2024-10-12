package com.example.movies.network

enum class HTTPStatusCode(val code:Int) {
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    SUCCESS(200)
}