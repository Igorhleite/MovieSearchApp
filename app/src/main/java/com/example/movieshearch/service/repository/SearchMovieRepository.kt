package com.example.movieshearch.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieshearch.model.SearchModel
import com.example.movieshearch.service.Constants
import com.example.movieshearch.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMovieRepository {

    //LiveDatas
    private val _movieListData = MutableLiveData<SearchModel>()
    val movieListData: LiveData<SearchModel>
        get() = _movieListData

    private val _movieNotExist = MutableLiveData<Boolean>()
    val movieNotExist: LiveData<Boolean>
        get() = _movieNotExist

    private val _responseControl = MutableLiveData<String>()
    val responseControl: LiveData<String>
        get() = _responseControl

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    fun findByName(queryParms: String) {
        val retrofiCall = RetrofitClient().service().searchByName(Constants.API_KEY, queryParms)
        retrofiCall.enqueue(object : Callback<SearchModel> {
            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                response.let {
                    if (it.code() == 200) {
                        val responsStatus = it.body()?.response
                        if (responsStatus == "True") {
                            _progress.value = false
                            _movieListData.value = it.body()
                        } else if (responsStatus == "False") {
                            _movieNotExist.value = true
                        }
                    }
                    else if (it.code() == 401) {
                        _responseControl.value = it.code().toString()
                    }
                }
            }
            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                _responseControl.value = "Erro na chamada Retrofit"
            }
        })
    }
}