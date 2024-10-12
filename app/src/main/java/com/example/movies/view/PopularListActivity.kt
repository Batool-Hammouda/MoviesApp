package com.example.movies.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.adapter.PopularListAdapter
import com.example.movies.databinding.ActivityPopularListBinding
import com.example.movies.repository.PopularMoviesRepository
import com.example.movies.viewmodel.PopularMoviesViewmodel
import com.example.movies.viewmodel.PopularViewmodelFactory
import kotlinx.coroutines.launch

class PopularListActivity : AppCompatActivity() {
    private lateinit var viewmodel: PopularMoviesViewmodel
    private lateinit var popularRecycler: RecyclerView
    private lateinit var popularListAdapter: PopularListAdapter

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listBinding: ActivityPopularListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_popular_list)

        popularRecycler = listBinding.popularRecycler
        popularRecycler.layoutManager = GridLayoutManager(this, 2)
        val progressBar = listBinding.progressBar
        progressBar.visibility

        val repo = PopularMoviesRepository()
        val factory = PopularViewmodelFactory(repo)
        viewmodel = ViewModelProvider(this, factory)[PopularMoviesViewmodel::class.java]


        lifecycleScope.launch {
            viewmodel.popularMoviesFlow.collect { movie ->
                popularListAdapter = PopularListAdapter(movie)
                popularRecycler.adapter = popularListAdapter
                progressBar.visibility = View.GONE
                popularListAdapter.submitList(movie)
            }
        }

        viewmodel.fetchMovies()
    }
}