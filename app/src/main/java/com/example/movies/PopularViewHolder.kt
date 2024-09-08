package com.example.movies

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.MovieCardBinding
import com.example.movies.model.Movie
import com.example.movies.model.Response

class PopularViewHolder(private val binding: MovieCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(movie: Movie) {
        binding.movieCard = movie
        Glide.with(binding.moviePic.context)
            .load("https://api.themoviedb.org/${movie.posterPath}")
            .into(binding.moviePic)
        binding.executePendingBindings()
    }

}