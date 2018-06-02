package com.example.raul.popmovies.login

import android.os.Handler
import android.util.Log
import com.example.raul.popmovies.Firebase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class PresenterSignup(view : Signup.View) : Signup.Presenter, OnCompleteListener<AuthResult> {

    val _view = view
    val authApi = Firebase(this@PresenterSignup)
    val TAG = "popmovies"
    var nomeUsu = ""

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
            authApi.createUserWithEmailAndPassword(email, senha)
            nomeUsu = nome
        }
    }

    override fun cadastradoComSucesso(userId: String) {
        _view.cadastradoComSucesso(userId)
    }

    override fun cadastroComFalha(erroMsg: String) {
        _view.cadastroComFalha(erroMsg)
    }

    override fun onComplete(task: Task<AuthResult>) {
        if(task.isSuccessful) {

            Log.d(TAG, "createUserWithEmailAndPassword:success")
            val userId = authApi.auth.currentUser!!.uid
            val user = authApi.dbUsers.child(userId)
            user.child("nome").setValue(nomeUsu)
            _view.esconderProgresso()
            _view.cadastradoComSucesso(userId)

        } else {
            Log.d(TAG, "createUserWithEmailAndPassword:failure")
            _view.esconderProgresso()
            _view.cadastroComFalha(task.exception.toString())
        }
    }
}