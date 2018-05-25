package com.example.raul.popmovies.login

class PresenterLogin(view: Login.View) : Login.Presenter {

    val _view : Login.View = view

    override fun logar(email: String, senha: String) {
        val emailValido =  !email.isNullOrBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val senhaValida = !senha.isNullOrBlank()

        if(!emailValido || !senhaValida) {
            if (!emailValido)
                _view.erroEmailFormatoIncorreto()

            if (!senhaValida)
                _view.erroSenhaFormatoIncorreto()
        } else {
//            var progressBar = _view.mostrarProgresso()
//            Handler().postDelayed({ _view.esconderProgresso() }, 3000)

            if(email.toLowerCase() == "email@example.com" && senha == "1")
                _view.autenticadoComSucesso()
            else
                _view.autenticacaoComFalha()
        }

    }
}