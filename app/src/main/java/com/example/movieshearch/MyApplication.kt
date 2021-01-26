package com.example.movieshearch

import android.app.Application
import androidx.room.Room
import com.example.movieshearch.service.repository.local.MovieDataBase

open class MyApplication : Application() {

    companion object {
        var database: MovieDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, MovieDataBase::class.java, "movieDB")
            .allowMainThreadQueries()
            .build()
    }

}