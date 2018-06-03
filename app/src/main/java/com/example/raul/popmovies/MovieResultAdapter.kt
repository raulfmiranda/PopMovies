package com.example.raul.popmovies

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.raul.popmovies.model.MovieResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_movie.*

class MovieResultAdapter(val movieResult: MovieResult) : RecyclerView.Adapter<MovieResultAdapter.ViewHolder>() {

    private val movies = movieResult.results
//    private val uriBase = "https://image.tmdb.org/t/p/w45"
    private val uriBase = "https://image.tmdb.org/t/p/w92"

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.let {
            it.txtMovieTitle?.text = movies[position].title

            val uri = Uri.parse(uriBase + movies[position].poster_path)
            Picasso
                    .get()
                    .load(uri.toString())
                    .placeholder(R.drawable.ic_movies)
                    .into(it.imgMovie)

//            https://image.tmdb.org/t/p/w45/to0spRl1CMDvyUbOnbb4fTk3VAd.jpg
//            val uri = Uri.parse(uriBase + movies[position].poster_path)
////            val inputStream = context?.contentResolver?.openInputStream(uri)
//            val context = it.txtMovieTitle?.context
//            val inputStream = context?.contentResolver?.openInputStream(uri)
//            val drawable = Drawable.createFromStream(inputStream, uri.toString())
//            it.txtMovieTitle?.setCompoundDrawables(drawable, null, null, null)
        }

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

