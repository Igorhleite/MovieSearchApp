package com.example.movieshearch.model

import com.google.gson.annotations.SerializedName

class MovieModel (

    @SerializedName("Title")
    var title: String = "",

    @SerializedName("Year")
    var year: String = "",

    @SerializedName("Type")
    var type: String = "",

    @SerializedName("imdbID")
    var imdbID: String = "",

    @SerializedName("Poster")
    var poster: String = ""
)
