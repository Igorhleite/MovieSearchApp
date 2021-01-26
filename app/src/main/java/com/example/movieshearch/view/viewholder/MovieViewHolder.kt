package com.example.movieshearch.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.view.listener.MovieListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieViewHolder(itemView: View, private val listener: MovieListener) :
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

        // atribuindo ações
        itemView.setOnClickListener {
            listener.onClick(model.imdbID)
        }

        itemView.favorite.setOnClickListener {
            if (itemView.favorite.text == it.context.getString(R.string.notFavorite)) {
                listener.onClickFavoriteButton(model)
                itemView.favorite.text = it.context.getString(R.string.isFavorite)
            } else {
                listener.onClickDisfavorButton(model)
                itemView.favorite.text = it.context.getString(R.string.notFavorite)
            }
        }
    }
}
