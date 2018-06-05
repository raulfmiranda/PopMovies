package com.example.raul.popmovies.fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.raul.popmovies.async.Firebase
import com.example.raul.popmovies.R
import com.example.raul.popmovies.model.Movie
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment(), ValueEventListener {

    private var movie: Movie? = null
    private var firebase = Firebase(this@DetailFragment)
    private val uriBase = "https://image.tmdb.org/t/p/w780"
    //https://image.tmdb.org/t/p/w300/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg

    companion object {
        val EXTRA_DETAIL = "EXTRA_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getSerializable(EXTRA_DETAIL) as Movie
            activity?.findViewById<Toolbar>(R.id.toolbar_main)?.title = movie?.title
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_detail, container, false)

        movie?.let {

            view.txtOverview.text = it.overview

            view.checkFavorite.setOnClickListener {
                movie?.let {
                    if(checkFavorite.isChecked) {
                        firebase.registerFavoriteMovie(it)
                    } else {
                        firebase.removeFavoriteMovie(it.id.toString())
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

    override fun onCancelled(dbError: DatabaseError?) {
        println("FavoritesFragment:onCancelled: ${dbError?.toException()}")
    }

    override fun onDataChange(data: DataSnapshot?) {
        var movieIDs = mutableListOf<Int>()
        data?.let {
            for(child in it.children) {
                var m = child.getValue(Movie::class.java)
                movieIDs.add(m!!.id)
            }
        }
        checkFavorite.isChecked = movieIDs.contains(movie!!.id)
    }
}
