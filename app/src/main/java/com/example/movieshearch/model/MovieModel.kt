package com.example.movieshearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorites")
class MovieModel(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("imdbID")
    var imdbID: String = "",

    @ColumnInfo(name = "title")
    @SerializedName("Title")
    var title: String = "",

    @ColumnInfo(name = "year")
    @SerializedName("Year")
    var year: String = "",

    @ColumnInfo(name = "type")
    @SerializedName("Type")
    var type: String = "",

    @ColumnInfo(name = "poster")
    @SerializedName("Poster")
    var poster: String = ""
)
