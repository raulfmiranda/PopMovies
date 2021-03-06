package com.example.raul.popmovies.adapters

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.raul.popmovies.MainActivity
import com.example.raul.popmovies.R
import com.example.raul.popmovies.fragments.DetailFragment
import com.example.raul.popmovies.model.Movie
import com.example.raul.popmovies.replaceFragment
import com.squareup.picasso.Picasso

class MovieResultAdapter(val _movies: MutableList<Movie>) : RecyclerView.Adapter<MovieResultAdapter.ViewHolder>() {

    private val movies = _movies
    private var context: Context? = null
//    private val uriBase = "https://image.tmdb.org/t/p/w45"
//    private val uriBase = "https://image.tmdb.org/t/p/w92"
    private val uriBase = "https://image.tmdb.org/t/p/w780"

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent?.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.let {

            it.txtMovieTitle?.let {
                it.text = movies[position].title
                it.setOnClickListener {
                    goToDetailFragment(position)
                }
            }

            it.imgMovie?.setOnClickListener {
                goToDetailFragment(position)
            }

            val uri = Uri.parse(uriBase + movies[position].backdrop_path)
//            val uri = Uri.parse(uriBase + movies[position].poster_path)
            Picasso
                    .get()
                    .load(uri.toString())
                    .placeholder(R.drawable.ic_movies)
                    .into(it.imgMovie)


        }
    }

    fun goToDetailFragment(position: Int) {
        var bundle = Bundle()
        bundle.putSerializable(DetailFragment.EXTRA_DETAIL, movies[position])
        var fragment = DetailFragment()
        fragment.arguments = bundle
        (context as MainActivity).replaceFragment(fragment, R.id.frame_fragment)
    }

    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
        var txtMovieTitle: TextView? = null
        var imgMovie: ImageView? = null
        init {
            txtMovieTitle = itemView?.findViewById(R.id.txtMovieTitle)
            imgMovie = itemView?.findViewById(R.id.imgMovie)
        }
    }
}

