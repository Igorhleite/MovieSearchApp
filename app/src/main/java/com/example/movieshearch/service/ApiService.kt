package com.example.movieshearch.service

import com.example.movieshearch.model.SearchModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?s=marvel")
    fun listMovie(@Query(value = "apikey", encoded = true) apiKey: String): Call<SearchModel>

    @GET(".")
    fun searchByName(@Query(value = "apikey", encoded = true) apiKey: String, @Query(value = "s", encoded = true) search: String): Call<SearchModel>

}

fun retrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(Constants.URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()