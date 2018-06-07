package com.example.raul.popmovies.async

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.Gson



class RetrofitInitializer(baseUrl: String) {

//    private val GSON = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    private val retrofit = Retrofit.Builder()
                                .baseUrl(baseUrl)
//                                .addConverterFactory(GsonConverterFactory.create(GSON))
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()

    fun moviesService() = retrofit.create(MoviesService::class.java)

}