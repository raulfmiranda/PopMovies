package com.example.raul.popmovies

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //TODO: https://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/
        //TODO: https://medium.com/@pedroimai/dicas-de-arquitetura-em-android-mvp-parte-2-7cda269e6b2b
        //TODO: https://www.youtube.com/watch?v=7o8jfvMhTSY

        // Trocando fonte do logotipo
        val typeFace:Typeface = Typeface.createFromAsset(assets, "fonts/true-crimes.ttf")
        tv_logo_signup.typeface = typeFace

        link_signup.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}
