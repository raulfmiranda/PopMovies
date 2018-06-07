package com.example.raul.popmovies.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.raul.popmovies.MainActivity
import com.example.raul.popmovies.adapters.MovieResultAdapter
import com.example.raul.popmovies.async.MoviesApi
import com.example.raul.popmovies.R
import com.example.raul.popmovies.async.DbWorkerThread
import com.example.raul.popmovies.dao.MovieDaoHelper
import com.example.raul.popmovies.dao.MovieDatabase
import com.example.raul.popmovies.model.Movie
import com.example.raul.popmovies.model.MovieResult
import com.example.raul.popmovies.toast
import kotlinx.android.synthetic.main.fragment_most_pop.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopFragment : Fragment(), Callback<MovieResult?> {

    private val mUiHandler = Handler()
    private var movieDaoHelper: MovieDaoHelper? = null
//    private val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity)?.let {
            movieDaoHelper = it.movieDaoHelper
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_most_pop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        fetchMoviesFromDb()
        super.onActivityCreated(savedInstanceState)
    }

    private fun fetchMoviesFromDb() {

        val task = Runnable {

            val movies = movieDaoHelper?.getAll()
            mUiHandler.post({

                if (movies == null || movies?.size == 0) {
                    MoviesApi.getMostPopMovies(this@MostPopFragment)
                }
                else {
                    movies?.let {
                        bindDataWithUi(it)
                    }
                }
            })
        }
        movieDaoHelper?.postTask(task)
    }

    private fun bindDataWithUi(movies: MutableList<Movie>) {
        movies.sortByDescending { it.popularity }
        fl_progress.visibility = FrameLayout.GONE
        recViewMostPop.layoutManager = LinearLayoutManager(activity)
        recViewMostPop.adapter = MovieResultAdapter(movies)
    }

    override fun onFailure(call: Call<MovieResult?>?, t: Throwable?) {
        val erroMsg = t?.message.toString()
        activity?.toast("Error: $erroMsg")
    }

    override fun onResponse(call: Call<MovieResult?>?, response: Response<MovieResult?>?) {
        response?.body()?.results?.let {
            bindDataWithUi(it)
            movieDaoHelper?.syncDb(it)
        }
    }
}
