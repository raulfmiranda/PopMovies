package com.example.raul.popmovies

import com.example.raul.popmovies.model.MovieResult
import com.example.raul.popmovies.model.Movie
import retrofit2.Callback

class MoviesApi {
    // API Reference: https://developers.themoviedb.org/3/movies/get-popular-movies

    companion object {
        // from: https://medium.com/code-better/hiding-api-keys-from-your-android-repository-b23f5598b906
        private val apiKey = BuildConfig.TheMoviedbApiKey
        private val baseUrl = "https://api.themoviedb.org/"
        private var movieResult: MovieResult = MovieResult(1, 0, 0, listOf<Movie>())
//        private val mostPopMovies = "3/movie/popular?api_key="
//        private val params = "&language=pt-BR&page=1"
        private val TAG = "popmovies"

        fun getMostPopMovies(callback: Callback<MovieResult?>) {
            // https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
//            val urlApi = "$baseUrl$mostPopMovies$apiKey$params"

            // TODO: https://medium.com/collabcode/consumindo-api-rest-no-android-com-retrofit-em-kotlin-parte-1-5e752ab8a877
            val call = RetrofitInitializer(baseUrl).moviesService().listMostPop(apiKey)

            call.enqueue(callback)
        }
    }
}