package com.example.raul.popmovies

interface Signup {

    interface View {
        fun erroNomeFormatoIncorreto()
        fun erroEmailFormatoIncorreto()
        fun erroSenhaFormatoIncorreto()
        fun cadastradoComSucesso()
        fun cadastroComFalha()
    }

    interface Presenter {
        fun cadastrar(nome:String, email:String, password:String)
    }
}