package com.example.raul.popmovies.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.raul.popmovies.async.Firebase
import com.example.raul.popmovies.adapters.MovieResultAdapter
import com.example.raul.popmovies.R
import com.example.raul.popmovies.model.Movie
import com.example.raul.popmovies.model.MovieResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), ValueEventListener {

    private var movies: MutableList<Movie> = mutableListOf<Movie>()
    private var firebase = Firebase(this@FavoritesFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebase.getFavoriteMovies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onCancelled(dbError: DatabaseError?) {
        println("FavoritesFragment:onCancelled: ${dbError?.toException()}")
    }

    override fun onDataChange(data: DataSnapshot?) {

        data?.let {
            for(child in it.children) {
                var movie = child.getValue(Movie::class.java)
                movies.add(movie!!)
            }
            recFavorites.layoutManager = LinearLayoutManager(activity)
            fl_progress.visibility = FrameLayout.GONE
            var movieResult = MovieResult(1, movies.size, 1, movies)
            recFavorites.adapter = MovieResultAdapter(movieResult.results)
        }
    }
}
