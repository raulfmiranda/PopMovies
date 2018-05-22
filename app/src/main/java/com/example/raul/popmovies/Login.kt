package com.example.raul.popmovies

interface Login {

    interface View {
        fun erroEmailFormatoIncorreto()
        fun erroSenhaFormatoIncorreto()
        fun autenticadoComSucesso()
        fun autenticacaoComFalha()
    }

    interface Presenter {
        fun logar(email:String, password:String)
    }
}