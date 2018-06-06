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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), Login.View {

    val presenter : Login.Presenter = LoginPresenter(this)
    val TAG : String = "popmovies"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Trocando fonte do logotipo
        val typeFace:Typeface = Typeface.createFromAsset(assets, "fonts/true-crimes.ttf")
        tv_logo_signup.typeface = typeFace

        link_signup.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            presenter.logar(input_email.text.toString(), input_password.text.toString())
        }
    }

    override fun erroEmailFormatoIncorreto() {
        val msg = getString(R.string.erro_email_bad_format)
        input_email.error = msg
    }

    override fun erroSenhaFormatoIncorreto() {
        val msg = getString(R.string.erro_password_bad_format)
        input_password.error = msg
    }

    override fun autenticadoComSucesso() {
        val msg = getString(R.string.success_authentication)
        Log.d(TAG, msg)
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun autenticacaoComFalha(msgErro: String) {
        val msg = getString(R.string.erro_authentication)
        Toast.makeText(this, msg + ": $msgErro", Toast.LENGTH_SHORT).show()
    }

    override fun mostrarProgresso() {
        fl_progress.visibility = FrameLayout.VISIBLE
    }

    override fun esconderProgresso() {
        fl_progress.visibility = FrameLayout.GONE
    }
}

/*
Base consulta:
* https://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/
* https://medium.com/@pedroimai/dicas-de-arquitetura-em-android-mvp-parte-2-7cda269e6b2b
* https://www.youtube.com/watch?v=7o8jfvMhTSY
*/
