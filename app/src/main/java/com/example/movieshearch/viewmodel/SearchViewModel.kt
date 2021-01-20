package com.example.movieshearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.service.repository.SearchMovieRepository


class SearchViewModel() : ViewModel() {

    private val searchMovieRepository: SearchMovieRepository =
        SearchMovieRepository()

    var listaFilmes = MutableLiveData<MutableList<MovieModel>>()

    var progress = MutableLiveData<Boolean>()

    fun retrofitCall(queryParams: String) {

        searchMovieRepository.findByName(queryParams) // chama a função no repository

        searchMovieRepository.movieListData.observeForever(Observer {
        listaFilmes.value = it.mMediaEntityList
      })
        searchMovieRepository.progress.observeForever(Observer {
            progress.value = it
        })
    }
}
/**
 * Lembrar sempre que o observer necessita de um LifecycleOwner, ou seja um objeto que defina seu
 * ciclo de vida, nesse caso foi utilizado o observerForever que implementa um observador e não exige um
 * lifecycleowner
 * */
