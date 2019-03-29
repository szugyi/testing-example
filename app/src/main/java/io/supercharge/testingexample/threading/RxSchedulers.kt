package io.supercharge.testingexample.threading

import android.os.HandlerThread
import android.os.Process
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

import java.util.concurrent.Executors

class RxSchedulers : Schedulers {
    private val looperThread: Scheduler

    init {
        val handlerThread = HandlerThread("Rx-Looper-Thread", Process.THREAD_PRIORITY_BACKGROUND)
        handlerThread.start()
        looperThread = AndroidSchedulers.from(handlerThread.looper)
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }

    override fun computation(): Scheduler {
        return io.reactivex.schedulers.Schedulers.computation()
    }

    override fun looper(): Scheduler {
        return looperThread
    }

    override fun synced(): Scheduler {
        return io.reactivex.schedulers.Schedulers.from(Executors.newSingleThreadExecutor())
    }
}
