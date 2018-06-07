package com.example.raul.popmovies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.raul.popmovies.async.DbWorkerThread
import com.example.raul.popmovies.dao.MovieDaoHelper
import com.example.raul.popmovies.dao.MovieDatabase
import com.example.raul.popmovies.fragments.FavoritesFragment
import com.example.raul.popmovies.fragments.MostPopFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.content.DialogInterface
import android.support.v7.app.AlertDialog


class MainActivity : AppCompatActivity() {

    var movieDaoHelper: MovieDaoHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        movieDaoHelper = MovieDaoHelper(this@MainActivity)
        movieDaoHelper?.deleteAllDb()

        addFragment(MostPopFragment(), R.id.frame_fragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_most_popular -> {
                    toolbar_main.title = getString(R.string.most_popular)
                    replaceFragment(MostPopFragment(), R.id.frame_fragment)
                }
                R.id.action_favorites -> {
                    toolbar_main.title = getString(R.string.favorites)
                    replaceFragment(FavoritesFragment(), R.id.frame_fragment)
                }
                else -> {
                    toolbar_main.title = getString(R.string.most_popular)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onDestroy() {
        movieDaoHelper?.destroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        showEndDialog()
    }

    private fun showEndDialog() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle(getString(R.string.close_app))
        builder.setMessage(getString(R.string.close_app_ask))

        builder.setPositiveButton(getString(R.string.yes), DialogInterface.OnClickListener { arg0, arg1 -> moveTaskToBack(true) })
        builder.setNegativeButton(getString(R.string.no), DialogInterface.OnClickListener { arg0, arg1 -> })

        val alerta = builder.create()
        alerta.show()
    }
}
