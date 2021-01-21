package com.example.movieshearch.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.service.repository.FavoriteMovieRepository

class FavoriteViewModel(context: Context) : ViewModel() {

    private val mContext = context
    private val mFavoriteMovieRepository: FavoriteMovieRepository =
        FavoriteMovieRepository(mContext)

    var listaFilmes = MutableLiveData<MutableList<MovieModel>>()


    fun save(movie: MovieModel) {
        val exist =
            mFavoriteMovieRepository.getById(movie.imdbID) //verificando se existe registro no BD com o ID do filme
        if (exist == null) { //caso não exista salva o filme no BD
            mFavoriteMovieRepository.save(movie)
        }
    }

    fun delete(movie: MovieModel) {
        mFavoriteMovieRepository.remove(movie)
    }

    fun getAll(){
        mFavoriteMovieRepository.getAll()
        mFavoriteMovieRepository.movieListData.observeForever(Observer {
            listaFilmes.value = it
        })
    }
}