package com.example.raul.popmovies.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.raul.popmovies.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * from movie")
    fun getAll(): MutableList<Movie>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: Movie)

    @Query("DELETE from movie")
    fun deleteAll()
}

// https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d