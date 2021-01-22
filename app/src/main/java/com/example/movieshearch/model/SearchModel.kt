package com.example.movieshearch.model

import com.google.gson.annotations.SerializedName

class SearchModel {
    @SerializedName("Search")
    var mMediaEntityList: MutableList<MovieModel>? = null

    @SerializedName("totalResults")
    var totalResults: String? = null

    @SerializedName("Response")
    var response: String? = null
}