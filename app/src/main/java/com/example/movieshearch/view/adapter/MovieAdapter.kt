package com.example.movieshearch.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshearch.view.viewholder.MovieViewHolder
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.view.listener.MovieListener

class MovieAdapter(var movies: MutableList<MovieModel>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private lateinit var mlistener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(item, mlistener)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    fun attachListener(listener: MovieListener) {
        mlistener = listener
    }
}