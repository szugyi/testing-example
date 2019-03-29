package io.supercharge.testingexample.threading

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class TestSchedulers : Schedulers {

    private val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
            // this prevents StackOverflowErrors when scheduling with a delay
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
        }
    }

    override fun ui(): Scheduler {
        return immediate
    }

    override fun io(): Scheduler {
        return immediate
    }

    override fun computation(): Scheduler {
        return immediate
    }

    override fun looper(): Scheduler {
        return immediate
    }

    override fun synced(): Scheduler {
        return immediate
    }
}
