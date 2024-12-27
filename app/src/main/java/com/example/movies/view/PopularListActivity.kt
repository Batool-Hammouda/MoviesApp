package com.example.movies.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
        val search = listBinding.search
        search.visibility = View.GONE
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
                search.visibility = View.VISIBLE
            }
        }
        viewmodel.searchedMovie.observe(this@PopularListActivity) { movies ->
            popularListAdapter.submitList(movies)
        }

        viewmodel.fetchMovies()


        search.setOnQueryTextListener(object:SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewmodel.searchForMovie(newText ?: "")
                return true
            }

        })


    }
}