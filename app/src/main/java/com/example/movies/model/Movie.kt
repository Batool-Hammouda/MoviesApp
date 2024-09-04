package com.example.movies.model

data class Movie(val adults:Boolean?,
                 val backdropPath: String?,
                 val genreId: List<Int>?,
                 val id: Int?,
                 val originalLanguage: String?,
                 val originalTitle: String?,
                 val overview:String?,
                 val popularity:Double?,
                 val posterPath:String?,
                 val releaseDate: String?,
                 val title: String?,
                 val video:Boolean?,
                 val voteAverage: Double?,
                 val voteCount: Int?
        )
