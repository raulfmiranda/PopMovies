package com.example.raul.popmovies

import android.widget.ProgressBar

interface Login {

    interface View {
        fun erroEmailFormatoIncorreto()
        fun erroSenhaFormatoIncorreto()
        fun autenticadoComSucesso()
        fun autenticacaoComFalha()
        fun mostrarProgresso() //: ProgressBar
        fun esconderProgresso()//(progressBar: ProgressBar)
    }

    interface Presenter {
        fun logar(email:String, password:String)
    }
}