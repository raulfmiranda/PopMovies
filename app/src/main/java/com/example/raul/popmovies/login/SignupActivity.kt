package com.example.raul.popmovies.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import com.example.raul.popmovies.MainActivity
import com.example.raul.popmovies.R
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
        input_name.error = msg
    }

    override fun erroEmailFormatoIncorreto() {
        val msg = getString(R.string.erro_email_bad_format)
        input_email.error = msg
    }

    override fun erroSenhaFormatoIncorreto() {
        val msg = getString(R.string.erro_password_bad_format)
        input_password.error = msg
    }

    override fun cadastradoComSucesso(userId: String) {
        val msg = getString(R.string.success_signup)
        Log.d(TAG, msg)
        Log.d(TAG, "UserId: $userId")
        var intent = Intent(this@SignupActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun cadastroComFalha(erroMsg: String) {
        val msg = getString(R.string.erro_signup)
        Log.d(TAG, "SignupActivity:cadastroComFalha: $erroMsg")
        Toast.makeText(this, msg + ": $erroMsg", Toast.LENGTH_SHORT).show()
    }

    override fun mostrarProgresso() {
        fl_progress.visibility = FrameLayout.VISIBLE
    }

    override fun esconderProgresso() {
        fl_progress.visibility = FrameLayout.GONE
    }
}
