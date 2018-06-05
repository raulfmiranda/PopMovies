package com.example.raul.popmovies.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.raul.popmovies.adapters.MovieResultAdapter
import com.example.raul.popmovies.async.MoviesApi
import com.example.raul.popmovies.R
import com.example.raul.popmovies.model.MovieResult
import com.example.raul.popmovies.toast
import kotlinx.android.synthetic.main.fragment_most_pop.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopFragment : Fragment(), Callback<MovieResult?> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MoviesApi.getMostPopMovies(this@MostPopFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_most_pop, container, false)
    }

    override fun onFailure(call: Call<MovieResult?>?, t: Throwable?) {
        val erroMsg = t?.message.toString()
        activity?.toast("Error: $erroMsg")
    }

    override fun onResponse(call: Call<MovieResult?>?, response: Response<MovieResult?>?) {
        recViewMostPop.layoutManager = LinearLayoutManager(activity)
        response?.body()?.let {
            fl_progress.visibility = FrameLayout.GONE
            recViewMostPop.adapter = MovieResultAdapter(it)
        }
    }
}
