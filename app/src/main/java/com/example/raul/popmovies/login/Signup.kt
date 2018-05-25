package com.example.raul.popmovies.login

interface Signup {

    interface View {
        fun erroNomeFormatoIncorreto()
        fun erroEmailFormatoIncorreto()
        fun erroSenhaFormatoIncorreto()
        fun cadastradoComSucesso(userId: String)
        fun cadastroComFalha(erroMsg:String)
        fun mostrarProgresso()
        fun esconderProgresso()
    }

    interface Presenter {
        fun cadastrar(nome:String, email:String, password:String)
        fun cadastradoComSucesso(userId:String)
        fun cadastroComFalha(erroMsg:String)
    }
}