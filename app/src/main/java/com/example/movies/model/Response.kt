package com.example.movies.model

import com.google.gson.annotations.SerializedName

data class Response(  @SerializedName("page")
                      val pageNum:Int,
                      @SerializedName("results")
                      val movies:List<Movie>,
                      @SerializedName("total_pages")
                      val totalPages:Int,
                      @SerializedName("total_results")
                      val totalResult:Int)
