package com.example.movieshearch.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieModel
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(model: MovieModel) {
        val movieTitle = itemView.findViewById<TextView>(R.id.movie_title_id)
        val movieYear = itemView.findViewById<TextView>(R.id.movie_year_id)
        val movieType = itemView.findViewById<TextView>(R.id.movie_type_id)
        val movieImage = itemView.findViewById<ImageView>(R.id.movie_image_id)

        // atribuindo valores
        movieTitle.text = model.title
        movieYear.text = model.year
        movieType.text = model.type.capitalize()
        Picasso.get().load(model.poster).into(movieImage)
    }
}
