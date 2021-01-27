package com.example.movieshearch.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.view.adapter.MovieAdapter
import com.example.movieshearch.view.listener.MovieListener
import com.example.movieshearch.viewmodel.FavoriteViewModel
import com.example.movieshearch.viewmodel.SearchViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(), MovieListener {

    private lateinit var searchViewModel: SearchViewModel
    private var movieAdapter: MovieAdapter = MovieAdapter(mutableListOf(), this)
    lateinit var favoriteViewModel: FavoriteViewModel
    var alertControl: Boolean = false
    private var mShimmerViewContainer: ShimmerFrameLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel = FavoriteViewModel()
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        implementObservers()
        mShimmerViewContainer = shimmer_view_container
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        search_button.setOnClickListener {
            searchMovieByName(search_edit_text.text.toString())
            mShimmerViewContainer?.isVisible = false
        }
    }

    private fun searchMovieByName(movieName: String) {
        movieAdapter.movies.clear()
        searchViewModel.searchMovie(movieName)
        alertControl = false
        shimmer_view_container.startShimmerAnimation()
        search_edit_text.onEditorAction(EditorInfo.IME_ACTION_DONE)
    }

    private fun implementObservers() {
        // observer's
        searchViewModel.movieList.observe(viewLifecycleOwner, Observer {
            movieAdapter = MovieAdapter(it, this)
            movieAdapter.notifyDataSetChanged()
            recycler_view_movies.adapter = movieAdapter
            alertControl = false
            shimmer_view_container.stopShimmerAnimation()
        })

        // for control loading
        searchViewModel.progress.observe(viewLifecycleOwner, Observer {
            shimmer_view_container.isVisible = it
        })

        // for control if movie exist
        searchViewModel.movieNotExist.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findMovieError(getString(R.string.errorTitle), getString(R.string.errorBody))
            }
        })

        // for control if response status
        searchViewModel.responseControl.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findMovieError(
                    getString(R.string.errorRequest),
                    getString(R.string.errorRequestBody)
                )
            }
        })
    }

    private fun findMovieError(title: String, message: String) {
        if(!alertControl) {
            MaterialAlertDialogBuilder(context as Context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(getString(R.string.ok)) { _, _ ->
                    search_edit_text.text.clear()
                    shimmer_view_container.isVisible = false
                }
                .show()
            alertControl = true
        }
    }

    override fun onClick(id: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("movieId", id)
        context?.startActivity(intent)
    }

    override fun onClickFavoriteButton(movie: MovieModel) {
        favoriteViewModel.save(movie)
    }

    override fun onClickDisfavorButton(movie: MovieModel) {
        favoriteViewModel.delete(movie)
    }

}