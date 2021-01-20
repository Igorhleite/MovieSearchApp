package com.example.movieshearch.service.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieshearch.model.SearchModel
import com.example.movieshearch.service.Constants
import com.example.movieshearch.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    val movieListData =
        MutableLiveData<SearchModel>() //implementa um live data "escutado" pela viewModel

    fun findByName(queryParms: String) {
        val retrofiCall = RetrofitClient().service().searchByName(Constants.API_KEY, queryParms)
        retrofiCall.enqueue(object : Callback<SearchModel> {
            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                response.let {
                    val responsStatus = it.body()?.response
                    if (responsStatus == "True") {
                        movieListData.value =
                            it.body() // atribui o valor ao live data e a viewModel é "notificada"
                    }
                }
            }

            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                Log.i("retrofit", "Falha na chamada retrofit")
            }
        })
    }
}

