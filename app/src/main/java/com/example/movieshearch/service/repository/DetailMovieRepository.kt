package com.example.movieshearch.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieshearch.model.MovieDetailModel
import com.example.movieshearch.service.Constants
import com.example.movieshearch.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieRepository {

    //liveData
    private val _movieDetailData = MutableLiveData<MovieDetailModel>()
    val movieDetailData: LiveData<MovieDetailModel>
        get() = _movieDetailData

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    fun findDetailById(movieId: String) {
        _progress.value = true
        val retrofitCall = RetrofitClient()
            .service().searchById(Constants.API_KEY, movieId)
        retrofitCall.enqueue(object : Callback<MovieDetailModel> {
            override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
                Log.i("retrofit", "Falha na chamada retrofit")
            }

            override fun onResponse(
                call: Call<MovieDetailModel>,
                response: Response<MovieDetailModel>
            ) {
                response.let {
                    _movieDetailData.value = it.body()
                    _progress.value = false
                }
            }
        })
    }
}