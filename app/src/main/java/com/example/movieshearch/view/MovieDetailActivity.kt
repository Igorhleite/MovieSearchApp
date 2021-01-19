//package com.example.movieshearch.view
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.movieshearch.R
//import com.example.movieshearch.model.MovieDetailModel
//import com.example.movieshearch.service.Constants
//import com.example.movieshearch.service.repository.remote.RetrofitClient
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.activity_movie_detail.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MovieDetailActivity : AppCompatActivity() {
//
//    private var movieId: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_movie_detail)
//
//        val movieId = intent.getStringExtra("movieId").toString()
//
//        val retrofitCall = RetrofitClient()
//            .service().searchById(Constants.API_KEY, movieId)
//        retrofitCall.enqueue(object :Callback<MovieDetailModel>{
//            override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<MovieDetailModel>,
//                response: Response<MovieDetailModel>
//            ) {
//                response.let {
//                    movie_title.text = response.body()?.title
//                    movie_gener.text = response.body()?.genre
//                    Picasso.get().load(response.body()?.poster).into(movie_poster)
//                    movie_rating.text = response.body()?.rating
//                    movie_time.text = response.body()?.time
//                    movie_released.text = response.body()?.released
//                    movie_description.text = response.body()?.overview
//                    movie_type.text = response.body()?.type
//                    movie_boxoffice.text = response.body()?.boxOffice
//                    movie_awards.text = response.body()?.awards
//                }
//            }
//
//        })
//
//    }
//
//}