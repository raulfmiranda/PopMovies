package com.example.raul.popmovies.model

import java.io.Serializable

data class Movie(
        var vote_count: Int = 0,
        var id: Int = 0,
        var video: Boolean = false,
        var vote_average: Double = 0.0,
        var title: String = "",
        var popularity: Double = 0.0,
        var poster_path: String = "",
        var original_language: String = "",
        var original_title: String = "",
        var genre_ids: List<Int> = mutableListOf<Int>(),
        var backdrop_path: String = "",
        var adult: Boolean = false,
        var overview: String = "",
        var release_date: String = ""
): Serializable