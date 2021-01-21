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

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _searchStatus = MutableLiveData<Boolean>()
    val searchStatus: LiveData<Boolean>
        get() = _searchStatus

    fun findByName(queryParms: String) {
        _searchStatus.value = false
        _progress.value = true
        val retrofiCall = RetrofitClient().service().searchByName(Constants.API_KEY, queryParms)
        retrofiCall.enqueue(object : Callback<SearchModel> {
            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                response.let {
                    val responsStatus = it.body()?.response
                    if (responsStatus == "True") {
                        _movieListData.value = it.body()
                        _progress.value = false
                    } else if (responsStatus == "False") {
                        _searchStatus.value = true
                        _progress.value = !_searchStatus.value!!
                    }
                }
            }
            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                Log.i("retrofit", "Falha na chamada retrofit")
            }
        })
    }
}