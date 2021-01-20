package com.example.movieshearch.service.repository.remote

import androidx.lifecycle.LiveData
import com.example.movieshearch.model.SearchModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    @GET(".")
//    fun searchById(@Query(value = "apikey", encoded = true) apiKey: String, @Query(value = "i", encoded = true) i: String ): Call<MovieDetailModel>

    @GET(".")
    fun searchByName(@Query(value = "apikey", encoded = true) apiKey: String, @Query(value = "s", encoded = true) search: String): Call<SearchModel>
}