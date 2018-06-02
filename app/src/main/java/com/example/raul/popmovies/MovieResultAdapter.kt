package com.example.raul.popmovies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.raul.popmovies.model.MovieResult

class MovieResultAdapter(val movieResult: MovieResult) : RecyclerView.Adapter<MovieResultAdapter.ViewHolder>() {

    private val movies = movieResult.results

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.txtMovieTitle?.text = movies[position].title
    }

    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
        var txtMovieTitle: TextView? = null
        init {
            txtMovieTitle = itemView?.findViewById(R.id.txtMovieTitle)
        }
    }
}

