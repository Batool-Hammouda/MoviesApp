package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movies.databinding.MovieCardBinding
import com.example.movies.model.Movie

class PopularListAdapter(private var movieList:List<Movie>): ListAdapter<Movie, PopularViewHolder>(DIFF){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        val binding=MovieCardBinding.inflate(layoutInflater,parent,false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val movie=movieList[position]
        holder.bindData(movie)
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}