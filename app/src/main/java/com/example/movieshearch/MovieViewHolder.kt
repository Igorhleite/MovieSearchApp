package com.example.movieshearch

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshearch.model.movieModel

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(model: movieModel) {
       val movieTitle = itemView.findViewById<TextView>(R.id.movie_title_id)
        val movieGener = itemView.findViewById<TextView>(R.id.movie_gener_id)
        val movieDuration = itemView.findViewById<TextView>(R.id.movie_duration_id)
        val movieCountry = itemView.findViewById<TextView>(R.id.movie_country_id)


        movieTitle.text = model.title
        movieGener.text = model.gender
        movieDuration.text = model.duration
        movieCountry.text = model.country

    }

}
