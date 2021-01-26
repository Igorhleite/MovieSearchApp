package com.example.movieshearch.view.listener

import com.example.movieshearch.model.MovieModel

interface MovieListener {
    fun onClick(id: String)
    fun onClickFavoriteButton(movie: MovieModel)
    fun onClickDisfavorButton(movie: MovieModel)
}