package com.example.movieshearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.service.repository.MovieRepository

class SearchViewModel() : ViewModel() {

    private val movieRepository: MovieRepository =
        MovieRepository()
    var listaFilmes = MutableLiveData<MutableList<MovieModel>>()

    fun retrofitCall(queryParams: String) {

        movieRepository.findByName(queryParams) // chama a função no repository

        movieRepository.movieListData.observeForever(Observer {
            listaFilmes.value = it.mMediaEntityList
        })
    }
}
/**
 * Lembrar sempre que o observer necessita de um LifecycleOwner, ou seja um objeto que defina seu
 * ciclo de vida, nesse caso foi utilizado o observerForever que implementa um observador e não exige um
 * lifecycleowner
 * */
