package com.example.movieshearch.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieshearch.R
import com.example.movieshearch.service.Constants
import com.example.movieshearch.view.adapter.MovieAdapter
import com.example.movieshearch.service.ApiService
import com.example.movieshearch.model.SearchModel
import com.example.movieshearch.model.MovieModel
import com.example.movieshearch.service.retrofit
import kotlinx.android.synthetic.main.fragment_home.recycler_view_movies
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        search_button.setOnClickListener {

            var queryParms = search_edit_text.text.toString()
            val dbug = queryParms

            retrofit()
                .create(ApiService::class.java)
                .searchByName(apiKey = Constants.API_KEY, search = queryParms)
                .enqueue(object : Callback<SearchModel> {

                    override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                        Toast.makeText(
                            context,
                            getString(R.string.api_error_msg),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<SearchModel>,
                        response: Response<SearchModel>
                    ) {
                        response?.let {
                            if (it.isSuccessful && it.code() == 200) {
                                val responseStatus = response.body()?.response as String

                                if (responseStatus == "True") {
                                    movieAdapter =
                                        MovieAdapter(response.body()?.mMediaEntityList as MutableList<MovieModel>)
                                    movieAdapter.notifyDataSetChanged()
                                    recycler_view_movies.adapter = movieAdapter
                                } else {
                                    movieAdapter.movies.clear()
                                    movieAdapter.notifyDataSetChanged()
                                    recycler_view_movies.adapter = movieAdapter

                                    Toast.makeText(
                                        context,
                                        getString(R.string.empty_search_message),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                })
        }
    }
}