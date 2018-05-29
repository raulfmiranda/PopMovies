package com.example.raul.popmovies

import com.example.raul.popmovies.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesService {

//    @GET("3/movie/popular?api_key={api_key}&language=pt-BR&page=1")
    @GET("3/movie/popular")
    fun listMostPop(@Query("api_key") apiKey: String): Call<MovieResult>
}