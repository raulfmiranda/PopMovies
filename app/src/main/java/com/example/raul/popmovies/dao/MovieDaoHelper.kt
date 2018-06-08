package com.example.raul.popmovies.dao

import android.content.Context
import com.example.raul.popmovies.async.DbWorkerThread
import com.example.raul.popmovies.model.Movie
import java.lang.reflect.Field
import java.lang.reflect.Type

class MovieDaoHelper(context: Context) {

    private var mDb: MovieDatabase? = null
    private var mDbWorkerThread: DbWorkerThread? = null

    init {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread?.start()
        mDb = MovieDatabase.getInstance(context)
    }

    fun getAll(): MutableList<Movie>? {
        return mDb?.movieDao()?.getAll()
    }

    fun postTask(task: Runnable) {
        mDbWorkerThread?.postTask(task)
    }

    fun insertMoviesInDb(movie: Movie) {
        // TODO: Gambiarra? Fazer tratamento com reflection ou Room
        if(movie.backdrop_path == null) {
            movie.backdrop_path = ""
        }
        if(movie.poster_path == null) {
            movie.poster_path = ""
        }
//        val fields = movie.javaClass.fields
//        for(f in fields) {
//            if(f.get(movie) == null) {
//            }
//        }
        val task = Runnable { mDb?.movieDao()?.insert(movie) }
        postTask(task)
    }

    fun syncDb(movies: MutableList<Movie>) {
        deleteAllDb()
        for(movie in movies) {
            insertMoviesInDb(movie)
        }
    }

    fun deleteAllDb() {
        val task = Runnable { mDb?.movieDao()?.deleteAll() }
        postTask(task)
    }

    fun destroy() {
        MovieDatabase.destroyInstance()
        mDbWorkerThread?.quit()
    }
}