package com.example.movieshearch.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun service() : ApiService{
        return retrofit.create(ApiService::class.java)
    }
}