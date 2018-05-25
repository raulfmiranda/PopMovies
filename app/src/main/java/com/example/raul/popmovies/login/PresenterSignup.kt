package com.example.raul.popmovies.login

import android.os.Handler
import com.example.raul.popmovies.Firebase

class PresenterSignup(view : Signup.View) : Signup.Presenter {

    val _view = view
    val authApi = Firebase(this)

    override fun cadastrar(nome: String, email: String, senha: String) {

        val nomeValido = !nome.isNullOrBlank()
        val emailValido =  !email.isNullOrBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val senhaValida = !senha.isNullOrBlank()

        if(!nomeValido || !emailValido || !senhaValida) {

            if(!nomeValido)
                _view.erroNomeFormatoIncorreto()

            if (!emailValido)
                _view.erroEmailFormatoIncorreto()

            if (!senhaValida)
                _view.erroSenhaFormatoIncorreto()

        } else {
            _view.mostrarProgresso()
            authApi.createUserWithEmailAndPassword(email, senha, nome)
            Handler().postDelayed({ _view.esconderProgresso() }, 3000)
        }
    }

    override fun cadastradoComSucesso(userId: String) {
        _view.cadastradoComSucesso(userId)
    }

    override fun cadastroComFalha(erroMsg: String) {
        _view.cadastroComFalha(erroMsg)
    }
}