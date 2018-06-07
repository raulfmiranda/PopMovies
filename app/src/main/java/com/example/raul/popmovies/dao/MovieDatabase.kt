package com.example.raul.popmovies.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.raul.popmovies.model.Movie
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration



@Database(entities = arrayOf(Movie::class), version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: MovieDatabase? = null

        // https://medium.com/google-developers/understanding-migrations-with-room-f01e04b07929
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE movie ADD COLUMN favorite INTEGER NOT NULL DEFAULT '0'")
            }
        }

        fun getInstance(context: Context): MovieDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MovieDatabase::class.java, "movie.db")
                            .addMigrations(MIGRATION_1_2) // caso queira migrar o banco sem perder dados antigos
//                            .fallbackToDestructiveMigration() // caso queira dropar o banco antigo na migração de versão
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
// https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d