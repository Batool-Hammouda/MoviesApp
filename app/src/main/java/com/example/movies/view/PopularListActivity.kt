package com.example.movies.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularListActivity : AppCompatActivity() {
    private lateinit var viewmodel: PopularMoviesViewmodel
    private lateinit var popularRecycler: RecyclerView
    private lateinit var popularListAdapter: PopularListAdapter

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listBinding: ActivityPopularListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_popular_list)


        val sharedpref = getSharedPreferences("AppSettingPrefs", MODE_PRIVATE)
        val darkMode = sharedpref.getBoolean("DarkMode", false)
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        popularRecycler = listBinding.popularRecycler
        popularRecycler.layoutManager = GridLayoutManager(this, 2)
        val progressBar = listBinding.progressBar
        progressBar.visibility
        val search = listBinding.search
        search.visibility = View.GONE

        val repo = PopularMoviesRepository()
        val factory = PopularViewmodelFactory(repo)
        viewmodel = ViewModelProvider(this, factory)[PopularMoviesViewmodel::class.java]

        listBinding.switchTheme.isChecked = darkMode

        listBinding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    listBinding.progressBar.visibility = View.VISIBLE
                }

                withContext(Dispatchers.IO) {
                    sharedpref.edit().putBoolean("DarkMode", isChecked).apply()
                }
                withContext(Dispatchers.Main) {
                    AppCompatDelegate.setDefaultNightMode(
                        if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                        else AppCompatDelegate.MODE_NIGHT_NO
                    )
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.popularMoviesFlow.collect { movie ->
                popularListAdapter = PopularListAdapter(movie)
                popularRecycler.adapter = popularListAdapter
                popularListAdapter.submitList(movie)
                popularRecycler.viewTreeObserver.addOnGlobalLayoutListener {
                    if (popularRecycler.childCount > 0) {
                        search.visibility = View.VISIBLE
                        progressBar.visibility=View.GONE
                    }
                }

            }
        }

        viewmodel.fetchMovies()

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewmodel.searchForMovie(newText?:"")
                return true
            }

        })
    }
}