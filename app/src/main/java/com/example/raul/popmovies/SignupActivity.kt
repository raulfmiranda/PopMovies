package com.example.raul.popmovies

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), Signup.View {

    val presenter : Signup.Presenter = PresenterSignup(this)
    val TAG : String = "popmovies"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Trocando fonte do logotipo
        val typeFace: Typeface = Typeface.createFromAsset(assets, "fonts/true-crimes.ttf")
        tv_logo_signup.typeface = typeFace

        link_signin.setOnClickListener {
            finish()
        }

        btn_signup.setOnClickListener {
            presenter.cadastrar(input_name.text.toString(), input_email.text.toString(), input_password.text.toString())
        }
    }

    override fun erroNomeFormatoIncorreto() {
        val msg = getString(R.string.erro_name_bad_format)
        input_name.setError(msg)
    }

    override fun erroEmailFormatoIncorreto() {
        val msg = getString(R.string.erro_email_bad_format)
        input_email.setError(msg)
    }

    override fun erroSenhaFormatoIncorreto() {
        val msg = getString(R.string.erro_password_bad_format)
        input_password.setError(msg)
    }

    override fun cadastradoComSucesso() {
        val msg = getString(R.string.success_signup)
        Log.d(TAG, msg)
    }

    override fun cadastroComFalha() {
        val msg = getString(R.string.erro_signup)
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
