package com.example.movieshearch.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshearch.MovieAdapter
import com.example.movieshearch.R
import com.example.movieshearch.model.mockMovies
import com.example.movieshearch.model.movieModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view_movies)
        recycler_view.adapter = MovieAdapter(mockMovies() as MutableList<movieModel>)
        recycler_view.layoutManager = LinearLayoutManager(context)
    }


}