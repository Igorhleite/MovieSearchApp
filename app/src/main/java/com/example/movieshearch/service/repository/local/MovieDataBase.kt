package com.example.movieshearch.service.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieshearch.model.MovieModel

@Database(entities = [MovieModel::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

}