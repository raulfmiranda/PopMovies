package com.example.raul.popmovies.async

import android.os.Handler
import android.os.HandlerThread
import android.util.Log

class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private var mWorkerHandler: Handler? = null

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mWorkerHandler?.post(task)
    }
}
// https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d