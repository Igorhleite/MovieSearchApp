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

    private val _movieNotExist = MutableLiveData<Boolean>()
    val movieNotExist: LiveData<Boolean>
        get() = _movieNotExist

    private val _responseControl = MutableLiveData<String>()
    val responseControl: LiveData<String>
        get() = _responseControl

    fun searchMovie(queryParams: String) {
        implementsObservers()
        _progress.value = true
        searchMovieRepository.findByName(queryParams)
    }

    private fun implementsObservers() {
        searchMovieRepository.movieListData.observeForever(Observer {
            _movieList.value = it.mMediaEntityList
        })

        searchMovieRepository.movieNotExist.observeForever(Observer {
            _movieNotExist.value = it
        })

        searchMovieRepository.progress.observeForever(Observer {
            _progress.value = it
        })
        searchMovieRepository.responseControl.observeForever(Observer {
            _responseControl.value = it
        })
    }
}