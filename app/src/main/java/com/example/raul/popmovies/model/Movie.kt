package com.example.raul.popmovies.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Ignore
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import java.io.Serializable

@Entity(tableName = "movie")
data class Movie(
        @ColumnInfo(name = "vote_count") var vote_count: Int = 0,
        @PrimaryKey(autoGenerate = false) var id: Int = 0,
        @ColumnInfo(name = "video") var video: Boolean = false,
        @ColumnInfo(name = "vote_average") var vote_average: Double = 0.0,
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "popularity") var popularity: Double = 0.0,
        @ColumnInfo(name = "poster_path") var poster_path: String = "",
        @ColumnInfo(name = "original_language") var original_language: String = "",
        @ColumnInfo(name = "original_title") var original_title: String = "",
        @Ignore var genre_ids: List<Int> = mutableListOf<Int>(),
        @ColumnInfo(name = "backdrop_path") var backdrop_path: String = "",
        @ColumnInfo(name = "adult") var adult: Boolean = false,
        @ColumnInfo(name = "overview") var overview: String = "",
        @ColumnInfo(name = "release_date") var release_date: String = "",
        @ColumnInfo(name = "favorite") var favorite: Boolean = false
): Serializable

// @ColumnInfo(name = "is_favorite") var is_favorite: Boolean = false
// error: Cannot find setter for field. private boolean is_favorite;
// Room não deixa criar propriedades com o nome começando com IS
// @NonNull from: import android.support.annotation.NonNull