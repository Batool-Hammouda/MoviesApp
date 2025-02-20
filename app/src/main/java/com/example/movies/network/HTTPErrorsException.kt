package com.example.movies.network

sealed class HTTPErrorsException(message:String):Throwable(message) {
    class UnauthorizedException : HTTPErrorsException("Unauthorized access. Please login again.")
    class NotFoundException : HTTPErrorsException("Resource not found.")
    class UnknownErrorException : HTTPErrorsException("An unknown error occurred.")
}