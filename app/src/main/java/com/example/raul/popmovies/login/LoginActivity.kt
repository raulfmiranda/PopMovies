package com.example.raul.popmovies.login

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.example.raul.popmovies.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), Login.View {

    val presenter : Login.Presenter = PresenterLogin(this)
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
    }

    override fun autenticacaoComFalha() {
        val msg = getString(R.string.erro_authentication)
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun mostrarProgresso() { //: ProgressBar {
        fl_progress.visibility = FrameLayout.VISIBLE
        pb_login.visibility = ProgressBar.VISIBLE
        //window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//        var progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
//        var params = RelativeLayout.LayoutParams(100, 100)
//        params.addRule(RelativeLayout.CENTER_IN_PARENT)
//        root_layout.addView(progressBar, params)
//        progressBar.visibility = View.VISIBLE
//        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//        return progressBar
    }

    override fun esconderProgresso() {//(progressBar: ProgressBar) {
        pb_login.visibility = ProgressBar.GONE
        fl_progress.visibility = FrameLayout.GONE
        //window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

//        progressBar.visibility = View.GONE
//        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}

/*
Base consulta:
* https://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/
* https://medium.com/@pedroimai/dicas-de-arquitetura-em-android-mvp-parte-2-7cda269e6b2b
* https://www.youtube.com/watch?v=7o8jfvMhTSY
*/
