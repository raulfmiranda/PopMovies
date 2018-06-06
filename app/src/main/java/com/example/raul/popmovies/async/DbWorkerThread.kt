package com.example.raul.popmovies.async

import android.os.Handler
import android.os.HandlerThread

class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private var mWorkerHandler: Handler? = null
//    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mWorkerHandler?.post(task)
//        mWorkerHandler?.post(task)
    }
}
// https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d