package com.example.raul.popmovies.async

import android.os.Handler
import android.os.HandlerThread
import android.util.Log

class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private var mWorkerHandler: Handler? = null
//    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        Log.d("MostPopFragment", "(7.3) dentro de onLooperPrepared e antes de mWorkerHandler = Handler(looper)")
        mWorkerHandler = Handler(looper)
        Log.d("MostPopFragment", "(7.4) dentro de onLooperPrepared e depois de mWorkerHandler = Handler(looper)")
    }

    fun postTask(task: Runnable) {
        Log.d("MostPopFragment", "(7.1) dentro de postTask e antes de mWorkerHandler?.post(task) - mWorkerHandler = ${mWorkerHandler.toString()}")
        mWorkerHandler?.post(task)
        Log.d("MostPopFragment", "(7.2) dentro de postTask e depois de mWorkerHandler?.post(task) - mWorkerHandler = ${mWorkerHandler.toString()}")
    }
}
// https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d