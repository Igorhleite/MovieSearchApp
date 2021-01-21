package com.example.movieshearch.service.repository.local

import androidx.room.*
import com.example.movieshearch.model.MovieModel

@Dao
interface MovieDAO {

    @Insert
    fun save(movie: MovieModel) : Long

    @Update
    fun update(movie: MovieModel) : Int

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun get(id: String): MovieModel

    @Query("SELECT * FROM favorites")
    fun getAll(): MutableList<MovieModel>

}