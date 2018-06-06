package com.example.raul.popmovies.model



data class MovieResult(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: MutableList<Movie> // <Movie>
)