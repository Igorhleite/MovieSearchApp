package com.example.movieshearch.service.repository

import androidx.lifecycle.MutableLiveData
import com.example.movieshearch.model.MovieDetailModel
import com.example.movieshearch.service.Constants
import com.example.movieshearch.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieRepository {

    val movieDetailData = MutableLiveData<MovieDetailModel>() // live data "escutado" pela viewModel

    val progress =
        MutableLiveData<Boolean>() //implementa um live data "escutado" pela viewModel que controla a progressBar

    fun findDetailById(movieId: String) {
        progress.value = true
        val retrofitCall = RetrofitClient()
            .service().searchById(Constants.API_KEY, movieId)
        retrofitCall.enqueue(object : Callback<MovieDetailModel> {
            override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<MovieDetailModel>,
                response: Response<MovieDetailModel>
            ) {
                response.let {
                    movieDetailData.value = it.body()
                    progress.value = false
                }
            }
        })
    }
}