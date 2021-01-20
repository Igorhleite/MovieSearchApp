package com.example.movieshearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movieshearch.model.MovieDetailModel
import com.example.movieshearch.service.Constants
import com.example.movieshearch.service.repository.DetailMovieRepository
import com.example.movieshearch.service.repository.SearchMovieRepository
import com.example.movieshearch.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val detailMovieRepository: DetailMovieRepository =
        DetailMovieRepository()

    var movieDetail = MutableLiveData<MovieDetailModel>()

    var progress = MutableLiveData<Boolean>()

    fun retrofitCall(movieId: String) {

        detailMovieRepository.findDetailById(movieId) //chama a função do retrofit no repository

        detailMovieRepository.movieDetailData.observeForever(Observer {
            movieDetail.value = it
        }) //observer que "obeserva" repository e atribui o valor ao movieDetail

        detailMovieRepository.progress.observeForever(Observer {
            progress.value = it
        })
    }
}
/**
 * Lembrar sempre que o observer necessita de um LifecycleOwner, ou seja um objeto que defina seu
 * ciclo de vida, nesse caso foi utilizado o observerForever que implementa um observador e não exige um
 * lifecycleowner
 * */