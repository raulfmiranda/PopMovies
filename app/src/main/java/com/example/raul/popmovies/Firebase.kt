package com.example.raul.popmovies

import android.util.Log
import com.example.raul.popmovies.login.Login
import com.example.raul.popmovies.login.Signup
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

//TODO: http://www.appsdeveloperblog.com/firebase-authentication-example-kotlin/

// COMO DESACOPLAR (TIRAR DEPENDÊNCIA) DA CLASSE FIREBASE DO PRESENTER????????
class Firebase(listener: OnCompleteListener<AuthResult>) {// : Login.Api {

    var auth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance().reference
    val listener = listener
    val dbUsers = db.child("Users")
    val TAG : String = "popmovies"

    fun createUserWithEmailAndPassword(email: String, senha: String) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(listener)
    }

    fun signInWithEmailAndPassword(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(listener)
    }

    fun signOut() {
        auth.signOut()
    }
}