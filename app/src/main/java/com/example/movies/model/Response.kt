package com.example.movies.model

data class Response(val pageNum:Int,
                    val movies:List<Movie>,
                    val totalPages:Int,
                    val totalResult:Int)
