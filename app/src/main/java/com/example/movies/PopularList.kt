package com.example.movies

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.repository.PopularMoviesRepository
import com.example.movies.adapter.PopularListAdapter
import com.example.movies.databinding.ActivityPopularListBinding
import com.example.movies.viewmodel.PopularMoviesViewmodel
import com.example.movies.viewmodel.PopularViewmodelFactory

class PopularList : AppCompatActivity() {
    private lateinit var viewmodel: PopularMoviesViewmodel
    private lateinit var popularRecycler: RecyclerView
    private lateinit var popularListAdapter: PopularListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listBinding: ActivityPopularListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_popular_list)

        popularListAdapter=PopularListAdapter(emptyList())
        popularRecycler = listBinding.popularRecycler
        popularRecycler.adapter=popularListAdapter
        popularRecycler.layoutManager = GridLayoutManager(this, 2)
        val repo = PopularMoviesRepository()
        val factory = PopularViewmodelFactory(repo)
        viewmodel = ViewModelProvider(this, factory).get(PopularMoviesViewmodel::class.java)
        viewmodel.fetchPopularMovies{response,error->
            if(response!=null){
                Log.d("Error Popular list", response.toString())
                popularListAdapter.updateMovies(response[0].movies)
            }else if(error!=null){
                Log.d("Error Popular list", error)
                Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
            }
        }



    }
}