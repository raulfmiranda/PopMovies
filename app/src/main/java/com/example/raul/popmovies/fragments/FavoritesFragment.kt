package com.example.raul.popmovies.fragments

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
import com.example.raul.popmovies.async.Firebase
import com.example.raul.popmovies.adapters.MovieResultAdapter
import com.example.raul.popmovies.R
import com.example.raul.popmovies.dao.MovieDaoHelper
import com.example.raul.popmovies.model.Movie
import com.example.raul.popmovies.model.MovieResult
import com.example.raul.popmovies.toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), ValueEventListener {

    private var movies: MutableList<Movie>? = null
    private var firebase = Firebase(this@FavoritesFragment)
    private var movieDaoHelper: MovieDaoHelper? = null
    private val mUiHandler = Handler()
    private val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDaoHelper = (activity as MainActivity).movieDaoHelper
        fetchFavMoviesFromDb()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    private fun fetchFavMoviesFromDb() {

        val task = Runnable {

            val movies = movieDaoHelper?.getFavorites()

            mUiHandler.post({
                if(movies == null || movies.size == 0) {
                    firebase.getFavoriteMovies()
                } else {
                    movies.let { it ->
                        bindDataWithUi(it)
                    }
                }
            })
        }
        movieDaoHelper?.postTask(task)
    }

    private fun bindDataWithUi(movies: MutableList<Movie>) {
        recFavorites.layoutManager = LinearLayoutManager(activity)
        fl_progress.visibility = FrameLayout.GONE
        val movieResult = MovieResult(1, movies.size, 1, movies)
        recFavorites.adapter = MovieResultAdapter(movieResult.results)
    }

    // Firebase onCancelled
    override fun onCancelled(dbError: DatabaseError) {
        activity?.toast(dbError.toException().toString())
    }

    // Firebase onDataChange
    override fun onDataChange(data: DataSnapshot) {

        movies?.let { movies ->
            for(child in data.children) {
                val movie = child.getValue(Movie::class.java)
                movie?.let { movies.add(it) }
            }
            bindDataWithUi(movies)
            movieDaoHelper?.updateFavorites(movies)
        }
        if (movies == null) {
            bindDataWithUi(mutableListOf<Movie>())
            activity?.toast(getString(R.string.no_favorites_movie))
        }
    }
}
