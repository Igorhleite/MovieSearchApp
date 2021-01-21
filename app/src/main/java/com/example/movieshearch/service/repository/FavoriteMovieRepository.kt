package com.example.movieshearch.service.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.service.repository.local.MovieDataBase

class FavoriteMovieRepository(context: Context){

    //liveData
    private val _movieListData = MutableLiveData<MutableList<MovieModel>>()
    val movieListData: LiveData<MutableList<MovieModel>>
    get() = _movieListData

    //access BD with DAO
    private val mDataBase = MovieDataBase.getInstance(context).movieDAO()

    fun save(movie: MovieModel): Boolean {
        return mDataBase.save(movie) > 0
    }

    fun getAll(): MutableList<MovieModel> {
        _movieListData.value = mDataBase.getAll()
        return movieListData.value!!
    }

    fun getById(id: String): MovieModel {
        return mDataBase.get(id)
    }

    fun remove(movie: MovieModel) {
        return mDataBase.remove(movie)
    }

}