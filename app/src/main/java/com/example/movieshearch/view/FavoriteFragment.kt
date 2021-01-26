package com.example.movieshearch.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.view.adapter.FavoriteMovieAdapter
import com.example.movieshearch.view.listener.MovieListener
import com.example.movieshearch.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class FavoriteFragment : Fragment(), MovieListener {

    private var movieAdapter: FavoriteMovieAdapter = FavoriteMovieAdapter(mutableListOf(), this)
    lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        favoriteViewModel = FavoriteViewModel()
        favoriteViewModel.getAll()
        implementsObservers()
    }

    private fun implementsObservers() {
        // observer's
        favoriteViewModel.movieList.observe(viewLifecycleOwner, Observer {
            movieAdapter = FavoriteMovieAdapter(it, this)
            movieAdapter.notifyDataSetChanged()
            recycler_view_movies.adapter = movieAdapter
        })
    }

    override fun onClick(id: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("movieId", id)
        context?.startActivity(intent)    }

    override fun onClickFavoriteButton(movie: MovieModel) {
        favoriteViewModel.save(movie)
    }

    override fun onClickDisfavorButton(movie: MovieModel) {
        favoriteViewModel.delete(movie)
    }
}

