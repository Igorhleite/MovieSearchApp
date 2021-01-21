package com.example.movieshearch.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.movieshearch.R
import com.example.movieshearch.view.adapter.FavoriteMovieAdapter
import com.example.movieshearch.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class FavoriteFragment : Fragment() {

    private  var movieAdapter: FavoriteMovieAdapter = FavoriteMovieAdapter(mutableListOf())
    lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel = FavoriteViewModel(context as Context)
        favoriteViewModel.getAll()

        favoriteViewModel.movieList.observe(viewLifecycleOwner, Observer {
            movieAdapter = FavoriteMovieAdapter(it)
            movieAdapter.notifyDataSetChanged()
            recycler_view_movies.adapter = movieAdapter
        })

        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}

