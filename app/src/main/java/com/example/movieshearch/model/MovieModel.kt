package com.example.movieshearch.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movie")
class MovieModel (

    @PrimaryKey
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
