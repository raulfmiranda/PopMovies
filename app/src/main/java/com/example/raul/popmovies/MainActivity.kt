package com.example.raul.popmovies

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

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
                    toolbar_main.title = getString(R.string.title_main_activity)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
