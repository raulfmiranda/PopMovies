package com.example.raul.popmovies

class PresenterSignup(view : Signup.View) : Signup.Presenter {

    val _view = view

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

            if(true) //TODO: Criação de conta com FireBase
                _view.cadastradoComSucesso()
            else
                _view.cadastroComFalha()
        }
    }
}