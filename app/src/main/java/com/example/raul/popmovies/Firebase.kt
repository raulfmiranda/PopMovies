package com.example.raul.popmovies

import android.util.Log
import com.example.raul.popmovies.login.Login
import com.example.raul.popmovies.login.Signup
import com.example.raul.popmovies.model.Movie
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//http://www.appsdeveloperblog.com/firebase-authentication-example-kotlin/

class Firebase constructor() {

    var auth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance().reference
    val dbUsers = db.child("Users")
    val dbFavorites = db.child("Favorites")
    var listenerAuthResult: OnCompleteListener<AuthResult>? = null
    var listenerValueEvent: ValueEventListener? = null
    val TAG : String = "popmovies"

    constructor(_listener: OnCompleteListener<AuthResult>) : this() {
        listenerAuthResult = _listener
    }

    constructor(_listener: ValueEventListener) : this() {
        listenerValueEvent = _listener
    }

    fun createUserWithEmailAndPassword(email: String, senha: String) {
        listenerAuthResult?.let {
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(it)
        }
    }

    fun signInWithEmailAndPassword(email: String, senha: String) {
        listenerAuthResult?.let {
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(it)
        }
    }

    fun registerUserData(userId: String, nome: String) {
        val userData = dbUsers.child(userId)
        userData.child("nome").setValue(nome)
    }

    fun registerFavoriteMovie(userId: String, movie: Movie) {
        val userFavorites = dbFavorites.child(userId).child(movie.id.toString())
        userFavorites.setValue(movie)
    }

    fun getFavoriteMovies(userId: String) {
        dbFavorites.child(userId).addListenerForSingleValueEvent(listenerValueEvent)
    }

    fun signOut() {
        auth.signOut()
    }
}