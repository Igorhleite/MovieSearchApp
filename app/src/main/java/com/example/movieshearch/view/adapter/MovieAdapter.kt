package com.example.movieshearch.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshearch.view.viewholder.MovieViewHolder
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.view.MovieDetailActivity


class MovieAdapter(var movies: MutableList<MovieModel>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val item = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(item)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

              holder.itemView.setOnClickListener {
                 val intent = Intent(it.context, MovieDetailActivity::class.java)
                  intent.putExtra("movieId", movie.imdbID)
                  it.context?.startActivity(intent)
              }
    }
}

