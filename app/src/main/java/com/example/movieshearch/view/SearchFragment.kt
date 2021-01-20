package com.example.movieshearch.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieshearch.R
import com.example.movieshearch.view.adapter.MovieAdapter
import com.example.movieshearch.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    private lateinit var shearchViewModel: SearchViewModel

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        shearchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        shearchViewModel.listaFilmes.observe(viewLifecycleOwner, Observer {
            movieAdapter = MovieAdapter(it)
            movieAdapter.notifyDataSetChanged()
            recycler_view_movies.adapter = movieAdapter
        })

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        search_button.setOnClickListener {
            val queryParams = search_edit_text.text.toString()
            shearchViewModel.retrofitCall(queryParams)
        }
    }
}