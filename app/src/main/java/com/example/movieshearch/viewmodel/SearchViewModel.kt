package com.example.movieshearch.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.model.SearchModel
import com.example.movieshearch.service.Constants
import com.example.movieshearch.service.repository.remote.RetrofitClient
import com.example.movieshearch.view.adapter.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel() : ViewModel() {

    private lateinit var movieAdapter: MovieAdapter

    val listaFilmes = MutableLiveData<MutableList<MovieModel>>()

    fun retrofitCall(queryParams: String) {

        val retrofitCalls = RetrofitClient()
            .service().searchByName(Constants.API_KEY, queryParams)
        retrofitCalls.enqueue(object : Callback<SearchModel> {
            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                Log.i("retrofit", "Falha na chamada retrofit")
            }

            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                response.let {
                    val responseStatus = response.body()?.response
                    if (responseStatus == "True") {
                        listaFilmes.value =
                            response.body()?.mMediaEntityList as MutableList<MovieModel>
                        Log.i("Valor da lista", "$listaFilmes")
                    }
                }
            }
        })
 }
}