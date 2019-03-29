package io.supercharge.testingexample.threading

import io.reactivex.Scheduler

/**
 * A `Scheduler` is an object that specifies an API for scheduling
 * units of work with or without delays or periodically.
 */
interface Schedulers {
    /**
     * A [Scheduler] which executes actions on the Android UI thread.
     *
     * @return a [Scheduler] intended for UI work
     */
    fun ui(): Scheduler

    /**
     * Returns a [Scheduler] intended for IO-bound work.
     *
     *
     * This can be used for asynchronously performing blocking IO.
     *
     *
     * Do not perform computational work on this scheduler. Use [.computation] instead.
     *
     *
     * Unhandled errors will be delivered to the scheduler Thread's [Thread.UncaughtExceptionHandler].
     *
     * @return a [Scheduler] intended for IO-bound work
     */
    fun io(): Scheduler

    /**
     * Returns a [Scheduler] intended for computational work.
     *
     *
     * This can be used for event-loops, processing callbacks and other computational work.
     *
     *
     * Do not perform IO-bound work on this scheduler. Use [.io] instead.
     *
     *
     * Unhandled errors will be delivered to the scheduler Thread's [Thread.UncaughtExceptionHandler].
     *
     * @return a [Scheduler] intended for computation-bound work
     */
    fun computation(): Scheduler

    /**
     * Returns a [Scheduler] intended for computational work a looper thread.
     *
     *
     * This can be used for specific api-s which require a looper.
     *
     *
     * Do not perform IO-bound work on this scheduler. Use [.io] instead.
     *
     *
     * Unhandled errors will be delivered to the scheduler Thread's [Thread.UncaughtExceptionHandler].
     *
     * @return a [Scheduler] intended for handler queues and looped api-s
     */
    fun looper(): Scheduler

    /**
     * Returns a [Scheduler] intended for serialized work.
     *
     *
     * This can be used for serialization work.
     *
     *
     * Do not perform IO-bound work on this scheduler. Use [.io] instead.
     *
     *
     * Unhandled errors will be delivered to the scheduler Thread's [Thread.UncaughtExceptionHandler].
     *
     * @return a [Scheduler] intended for serialized work
     */
    fun synced(): Scheduler
}
