package com.example.movies.adapter

import android.icu.text.DecimalFormat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.MovieCardBinding
import com.example.movies.model.Movie

class PopularViewHolder(private val binding: MovieCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(movie: Movie) {
        binding.title.text = movie.title
        binding.year.text = movie.releaseDate
        val rateFormat=DecimalFormat("#.#")
        val rate=rateFormat.format(movie.voteAverage)
        binding.rate.text=rate.toString()
        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide.with(binding.moviePic.context)
            .load(posterUrl)
            .into(binding.moviePic)
        binding.executePendingBindings()
    }

}