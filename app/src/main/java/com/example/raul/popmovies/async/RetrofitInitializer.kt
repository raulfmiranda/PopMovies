package com.example.raul.popmovies.async

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer(baseUrl: String) {

    private val retrofit = Retrofit.Builder()
                                .baseUrl(baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()

    fun moviesService() = retrofit.create(MoviesService::class.java)

}