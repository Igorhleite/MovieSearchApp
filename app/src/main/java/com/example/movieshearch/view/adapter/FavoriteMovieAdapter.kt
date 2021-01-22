package com.example.movieshearch.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshearch.view.viewholder.MovieViewHolder
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.view.MovieDetailActivity
import com.example.movieshearch.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.movie_item.view.*


class FavoriteMovieAdapter(var movies: MutableList<MovieModel>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(item)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        favoriteViewModel = FavoriteViewModel(holder.itemView.context)

        holder.itemView.favorite.text = holder.itemView.context.getString(R.string.isFavorite)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, MovieDetailActivity::class.java)
            intent.putExtra("movieId", movie.imdbID)
            it.context?.startActivity(intent)
        }

        holder.itemView.favorite.setOnClickListener {
            if (holder.itemView.favorite.text == it.context.getString(R.string.notFavorite)) {
                favoriteViewModel.save(movie)
                holder.itemView.favorite.text = it.context.getString(R.string.isFavorite)
            } else {
                favoriteViewModel.delete(movie)
                holder.itemView.favorite.text = it.context.getString(R.string.notFavorite)
            }
        }
    }
}