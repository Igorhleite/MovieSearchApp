package com.example.movieshearch.service.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.model.SearchModel
import com.example.movieshearch.service.repository.local.MovieDataBase

class FavoriteMovieRepository(context: Context){

    val movieListData = MutableLiveData<MutableList<MovieModel>>() //implementa um live data "escutado" pela viewModel

    private val mDataBase = MovieDataBase.getInstance(context).movieDAO()

    fun save(movie: MovieModel): Boolean {
        return mDataBase.save(movie) > 0
    }

    fun getAll(): MutableList<MovieModel> {
        movieListData.value = mDataBase.getAll()
        return movieListData.value!!
    }

    fun getById(id: String): MovieModel {
        return mDataBase.get(id)
    }

}