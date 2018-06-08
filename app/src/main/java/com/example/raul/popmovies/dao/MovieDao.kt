package com.example.raul.popmovies.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.example.raul.popmovies.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * from movie")
    fun getAll(): MutableList<Movie>

    @Query("SELECT * from movie WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): Movie

    @Query("SELECT * from movie WHERE favorite = 1")
    fun getFavorites(): MutableList<Movie>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: Movie)

    @Update
    fun update(movie: Movie)

    @Query("DELETE from movie")
    fun deleteAll()
}

// https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d