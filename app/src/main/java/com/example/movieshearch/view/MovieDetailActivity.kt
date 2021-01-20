package com.example.movieshearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieDetailModel
import com.example.movieshearch.service.Constants
import com.example.movieshearch.service.repository.remote.RetrofitClient
import com.example.movieshearch.viewmodel.DetailViewModel
import com.example.movieshearch.viewmodel.SearchViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.progressBar
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var movieId = intent.getStringExtra("movieId").toString()

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        detailViewModel.retrofitCall(movieId)

        detailViewModel.movieDetail.observe(this, Observer {
            movie_title.text = it.title
            movie_gener.text = it.genre
            Picasso.get().load(it.poster).into(movie_poster)
            movie_rating.text = it.rating
            movie_time.text = it.time
            movie_released.text = it.released
            movie_description.text = it.overview
            movie_type.text = it.type
            movie_boxoffice.text = it.boxOffice
            movie_awards.text = it.awards
        })

        detailViewModel.progress.observe(this, Observer {
            progressBar.isVisible = it
        })
    }
}