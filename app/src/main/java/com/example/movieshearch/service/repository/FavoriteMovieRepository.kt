package com.example.movieshearch.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieshearch.MyApplication
import com.example.movieshearch.model.MovieModel

class FavoriteMovieRepository() {

    //liveData
    private val _movieListData = MutableLiveData<MutableList<MovieModel>>()
    val movieListData: LiveData<MutableList<MovieModel>>
        get() = _movieListData

    //access BD with DAO
    private val mDataBase = MyApplication.database?.movieDAO()
    private var saved: Boolean = false
     var obtainedById: MovieModel = MovieModel()

    fun save(movie: MovieModel): Boolean {
        if (mDataBase != null) {
            saved = mDataBase.save(movie) > 0
        }
        return saved
    }

    fun getAll(): MutableList<MovieModel> {
        if (mDataBase != null) {
            _movieListData.value = mDataBase.getAll()
        }
        return movieListData.value!!
    }

    fun getById(id: String): MovieModel {
        if (mDataBase != null) {
            obtainedById = mDataBase.get(id)
        }
        return obtainedById
    }

    fun remove(movie: MovieModel) {
        if (mDataBase != null) {
            return mDataBase.remove(movie)
        }
    }
}