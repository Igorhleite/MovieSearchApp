package com.example.movieshearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.service.repository.SearchMovieRepository


class SearchViewModel() : ViewModel() {

    private val searchMovieRepository: SearchMovieRepository =
        SearchMovieRepository()

    private val _movieList = MutableLiveData<MutableList<MovieModel>>()
    val movieList: LiveData<MutableList<MovieModel>>
        get() = _movieList

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _searchStatus = MutableLiveData<Boolean>()
    val searchStatus: LiveData<Boolean>
        get() = _searchStatus

    fun searchMovie(queryParams: String) {
        searchMovieRepository.findByName(queryParams)
        searchMovieRepository.movieListData.observeForever(Observer {
            _movieList.value = it.mMediaEntityList
        })
        searchMovieRepository.progress.observeForever(Observer {
            _progress.value = it
        })
        searchMovieRepository.searchStatus.observeForever(Observer {
            _searchStatus.value = it
        })
    }

}