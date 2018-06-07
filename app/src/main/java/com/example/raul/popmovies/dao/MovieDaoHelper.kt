package com.example.raul.popmovies.dao

import android.content.Context
import com.example.raul.popmovies.async.DbWorkerThread
import com.example.raul.popmovies.model.Movie

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