package com.example.movieshearch.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(), MovieListener {

    private lateinit var shearchViewModel: SearchViewModel
    private var movieAdapter: MovieAdapter = MovieAdapter(mutableListOf(), this)
    lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel = FavoriteViewModel()
        shearchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        implementObservers()
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        search_button.setOnClickListener {
            searchMovieByName(search_edit_text.text.toString())
        }
    }

    private fun searchMovieByName(movieName: String) {
        shearchViewModel.searchMovie(movieName)
        search_edit_text.onEditorAction(EditorInfo.IME_ACTION_DONE)
    }

    private fun implementObservers() {
        // observer's
        shearchViewModel.movieList.observe(viewLifecycleOwner, Observer {
            movieAdapter = MovieAdapter(it, this)
            movieAdapter.notifyDataSetChanged()
            recycler_view_movies.adapter = movieAdapter
        })

//        // for control loading
        shearchViewModel.progress.observe(viewLifecycleOwner, Observer {
            progressBar.isVisible = it
        })

        // for control if movie exist
        shearchViewModel.movieNotExist.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                MaterialAlertDialogBuilder(context as Context)
                    .setTitle(getString(R.string.errorTitle))
                    .setMessage(getString(R.string.errorBody))
                    .setNeutralButton(getString(R.string.ok)) { _, _ ->
                        search_edit_text.text.clear()
                    }
                    .show()
            }
        })

        // for control if response status
        shearchViewModel.responseControl.observe(viewLifecycleOwner, Observer {
            MaterialAlertDialogBuilder(context as Context)
                .setTitle(getString(R.string.errorRequest))
                .setMessage(getString(R.string.errorRequestBody))
                .setNeutralButton(getString(R.string.ok)) { _, _ ->
                    search_edit_text.text.clear()
                }
                .show()
        })
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