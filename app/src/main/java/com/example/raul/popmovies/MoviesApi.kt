package com.example.raul.popmovies

import android.provider.Settings.Global.getString
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.raul.popmovies.model.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesApi {
    // API Reference: https://developers.themoviedb.org/3/movies/get-popular-movies

    companion object {
        // from: https://medium.com/code-better/hiding-api-keys-from-your-android-repository-b23f5598b906
        private val apiKey = BuildConfig.TheMoviedbApiKey
        private val baseUrl = "https://api.themoviedb.org/"
        private var movieResult: MovieResult = MovieResult(1, 0, 0, listOf<MovieResult.Result>())
//        private val mostPopMovies = "3/movie/popular?api_key="
//        private val params = "&language=pt-BR&page=1"
        private val TAG = "popmovies"

        fun getMostPopMovies(recyclerView: RecyclerView) {
            // https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
//            val urlApi = "$baseUrl$mostPopMovies$apiKey$params"

            // TODO: https://medium.com/collabcode/consumindo-api-rest-no-android-com-retrofit-em-kotlin-parte-1-5e752ab8a877
            val call = RetrofitInitializer(baseUrl).moviesService().listMostPop(apiKey)

            call.enqueue(object: Callback<MovieResult?> {
                override fun onFailure(call: Call<MovieResult?>?, t: Throwable?) {
                    Log.d(TAG, "getMostPopMovies erro: " + t?.message)
                }

                override fun onResponse(call: Call<MovieResult?>?, response: Response<MovieResult?>?) {
                    response?.body()?.let {
                        loadMostPopMovieOnRecyclerView(recyclerView)
                        movieResult = it
                    }
                }
            })
        }

        fun loadMostPopMovieOnRecyclerView(recyclerView: RecyclerView) {
            recyclerView.adapter = MovieResultAdapter(movieResult)
        }
    }
}