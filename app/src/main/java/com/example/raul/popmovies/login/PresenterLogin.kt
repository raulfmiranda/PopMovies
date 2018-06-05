package com.example.raul.popmovies.login

import com.example.raul.popmovies.async.Firebase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class PresenterLogin(view: Login.View) : Login.Presenter, OnCompleteListener<AuthResult> {

    val _view : Login.View = view
    val authApi = Firebase(this@PresenterLogin)

    override fun logar(email: String, senha: String) {
        val emailValido =  !email.isNullOrBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val senhaValida = !senha.isNullOrBlank()

        if(!emailValido || !senhaValida) {
            if (!emailValido)
                _view.erroEmailFormatoIncorreto()

            if (!senhaValida)
                _view.erroSenhaFormatoIncorreto()
        } else {
            var progressBar = _view.mostrarProgresso()
            authApi.signInWithEmailAndPassword(email, senha)
        }
    }

    override fun onComplete(task: Task<AuthResult>) {
        if(task.isSuccessful) {
            _view.esconderProgresso()
            _view.autenticadoComSucesso()
        } else {
            _view.esconderProgresso()
            _view.autenticacaoComFalha(task.exception.toString())
        }
    }
}