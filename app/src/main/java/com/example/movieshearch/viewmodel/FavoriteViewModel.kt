package com.example.movieshearch.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.service.repository.FavoriteMovieRepository

class FavoriteViewModel() : ViewModel() {

    private val mFavoriteMovieRepository: FavoriteMovieRepository =
        FavoriteMovieRepository()

    //liveData
    private val _movieList = MutableLiveData<MutableList<MovieModel>>()
    val movieList: LiveData<MutableList<MovieModel>>
        get() = _movieList

    fun save(movie: MovieModel) { //verify if exist movie em BD
        val exist =
            mFavoriteMovieRepository.getById(movie.imdbID)
        if (exist == null) {
            mFavoriteMovieRepository.save(movie)
        }
    }

    fun delete(movie: MovieModel) {
        mFavoriteMovieRepository.remove(movie)
    }

    fun getAll() {
        mFavoriteMovieRepository.getAll()
        mFavoriteMovieRepository.movieListData.observeForever(Observer {
            _movieList.value = it
        })
    }
}