package com.example.raul.popmovies.login

import android.widget.ProgressBar

interface Login {

    interface View {
        fun erroEmailFormatoIncorreto()
        fun erroSenhaFormatoIncorreto()
        fun autenticadoComSucesso()
        fun autenticacaoComFalha(msgErro: String)
        fun mostrarProgresso() //: ProgressBar
        fun esconderProgresso()//(progressBar: ProgressBar)
    }

    interface Presenter {
        fun logar(email:String, senha:String)
    }

//    interface Api {
//        fun signInWithEmailAndPassword(email:String, senha:String)
//        fun createUserWithEmailAndPassword(email:String, senha:String, nome:String)
//        fun signOut()
//    }

}