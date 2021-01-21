package com.example.movieshearch.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieshearch.model.MovieModel

@Database(entities = [MovieModel::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    companion object {

        private lateinit var INSTANCE: MovieDataBase

        fun getInstance(context: Context): MovieDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(MovieDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, MovieDataBase::class.java, "movieDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}