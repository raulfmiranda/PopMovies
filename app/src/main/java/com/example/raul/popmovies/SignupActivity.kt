package com.example.raul.popmovies

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Trocando fonte do logotipo
        val typeFace: Typeface = Typeface.createFromAsset(assets, "fonts/true-crimes.ttf")
        tv_logo_signup.typeface = typeFace

        link_signin.setOnClickListener {
            finish()
        }
    }
}
