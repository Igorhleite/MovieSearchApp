package com.example.movieshearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieshearch.R
import com.example.movieshearch.model.MovieModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getStringExtra("movieId")
        text_teste.text = movieId

    }
}