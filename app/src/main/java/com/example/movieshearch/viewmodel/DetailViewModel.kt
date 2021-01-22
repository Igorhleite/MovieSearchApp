package com.example.movieshearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movieshearch.model.MovieDetailModel
import com.example.movieshearch.service.repository.DetailMovieRepository


class DetailViewModel : ViewModel() {

    private val detailMovieRepository: DetailMovieRepository = DetailMovieRepository()

    //liveData
    private val _movieDetail = MutableLiveData<MovieDetailModel>()
    val movieDetail: LiveData<MovieDetailModel>
        get() = _movieDetail

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    fun searchMovieById(movieId: String) {
        detailMovieRepository.findDetailById(movieId)

        detailMovieRepository.movieDetailData.observeForever(Observer {
            _movieDetail.value = it
        })
        detailMovieRepository.progress.observeForever(Observer {
            _progress.value = it
        })
    }
}