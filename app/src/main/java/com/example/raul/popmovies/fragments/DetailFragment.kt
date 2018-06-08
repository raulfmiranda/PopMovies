package com.example.raul.popmovies.fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.raul.popmovies.MainActivity
import com.example.raul.popmovies.async.Firebase
import com.example.raul.popmovies.R
import com.example.raul.popmovies.dao.MovieDaoHelper
import com.example.raul.popmovies.model.Movie
import com.example.raul.popmovies.toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment(), ValueEventListener {

    private var movie: Movie? = null
    private var firebase = Firebase(this@DetailFragment)
    private var movieDaoHelper: MovieDaoHelper? = null
    private val uriBase = "https://image.tmdb.org/t/p/w780"
    //https://image.tmdb.org/t/p/w300/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg

    companion object {
        val EXTRA_DETAIL = "EXTRA_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getSerializable(EXTRA_DETAIL) as Movie
            (activity as MainActivity)?.let {
                it.findViewById<Toolbar>(R.id.toolbar_main)?.title = movie?.title
                movieDaoHelper = it.movieDaoHelper
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_detail, container, false)

        movie?.let {

            view.txtOverview.text = it.overview
            view.txtOverview.movementMethod = ScrollingMovementMethod()

            view.checkFavorite.setOnClickListener {
                movie?.let {
                    if(checkFavorite.isChecked) {
                        registerFavoriteMovie(it)
                    } else {
                        removeFavoriteMovie(it)
                    }
                }
            }

            firebase.getFavoriteMovies()

            val uri = Uri.parse(uriBase + it.backdrop_path)
            Picasso
                    .get()
                    .load(uri.toString())
                    .placeholder(R.drawable.no_movie_img)
                    .fit().centerCrop()
                    .into(view.imgMovieDetail)

        }
        return view
    }

    fun registerFavoriteMovie(movie: Movie) {
        movie.favorite = true
        movieDaoHelper?.update(movie)
        firebase.registerFavoriteMovie(movie)
    }

    fun removeFavoriteMovie(movie: Movie) {
        movie.favorite = false
        movieDaoHelper?.update(movie)
        firebase.removeFavoriteMovie(movie.id.toString())
    }

    override fun onCancelled(dbError: DatabaseError) {
        activity?.toast(dbError.toException().toString())
    }

    override fun onDataChange(data: DataSnapshot) {
        var movieIDs = mutableListOf<Int>()
        for(child in data.children) {
            var m = child.getValue(Movie::class.java)
            movieIDs.add(m!!.id)
        }
        checkFavorite.isChecked = movieIDs.contains(movie!!.id)
    }
}
