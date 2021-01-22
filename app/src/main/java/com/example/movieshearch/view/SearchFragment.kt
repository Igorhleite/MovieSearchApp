package com.example.movieshearch.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieshearch.R
import com.example.movieshearch.view.adapter.MovieAdapter
import com.example.movieshearch.viewmodel.SearchViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    private lateinit var shearchViewModel: SearchViewModel
    private var movieAdapter: MovieAdapter = MovieAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        search_button.setOnClickListener {
            val queryParams = search_edit_text.text.toString()
            shearchViewModel.searchMovie(queryParams)
            search_edit_text.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }

        shearchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        shearchViewModel.movieList.observe(viewLifecycleOwner, Observer {
            movieAdapter = MovieAdapter(it)
            movieAdapter.notifyDataSetChanged()
            recycler_view_movies.adapter = movieAdapter
        })
        shearchViewModel.progress.observe(viewLifecycleOwner, Observer {
            progressBar.isVisible = it
        })

        shearchViewModel.searchStatus.observe(viewLifecycleOwner, Observer {
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
    }
}