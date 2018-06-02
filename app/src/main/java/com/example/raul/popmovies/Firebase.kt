package com.example.raul.popmovies

import android.util.Log
import com.example.raul.popmovies.login.Login
import com.example.raul.popmovies.login.Signup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

//TODO: http://www.appsdeveloperblog.com/firebase-authentication-example-kotlin/

// COMO DESACOPLAR (TIRAR DEPENDÊNCIA) DA CLASSE FIREBASE DO PRESENTER????????
class Firebase(presenterSignup: Signup.Presenter) : Login.Api {

    var auth = FirebaseAuth.getInstance()
    var db = FirebaseDatabase.getInstance()
    var dbRef = db.reference.child("Users")
    val presenter = presenterSignup
    val TAG : String = "popmovies"

    override fun createUserWithEmailAndPassword(email: String, senha: String, nome: String) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { task ->

            if(task.isSuccessful) {
                Log.d(TAG, "createUserWithEmailAndPassword:success")
                val userId = auth.currentUser!!.uid
                val user = dbRef.child(userId)
                user.child("nome").setValue(nome)
                presenter.cadastradoComSucesso(userId)

            } else {
                presenter.cadastroComFalha(task.exception.toString())
                Log.d(TAG, "createUserWithEmailAndPassword:failure")
            }
        }
    }

    override fun signInWithEmailAndPassword(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { task ->

            if(task.isSuccessful) {
                Log.d(TAG, "signInWithEmailAndPassword:success")
            } else {
                Log.d(TAG, "signInWithEmailAndPassword:failure")
            }
        }
    }

    override fun signOut() {
        auth.signOut()
    }
}