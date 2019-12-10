package io.supercharge.testingexample.domain.repository

import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.threading.Schedulers
import io.supercharge.testingexample.threading.threadName
import timber.log.Timber
import java.util.Collections
import javax.inject.Inject

class NoteRepository
@Inject internal constructor(
    private val schedulers: Schedulers
) {

    private val notesProcessor: FlowableProcessor<List<Note>> = BehaviorProcessor.create<List<Note>>()

    fun saveNotes(noteList: List<Note>): Completable = Completable.fromCallable {
        Timber.i("Saving notes to repository on $threadName")

        Thread.sleep(25)

        notesProcessor.onNext(noteList)
    }.subscribeOn(schedulers.io())
        .observeOn(schedulers.computation())

    fun getNoteList(): Flowable<List<Note>> {
        return Flowable.create<List<Note>>({ source ->
            val disposable = notesProcessor
                .subscribe({ source.onNext(it) }, { source.onError(it) })

            source.setCancellable { disposable.dispose() }
        }, BackpressureStrategy.BUFFER)
            .onErrorResumeNext(Flowable.just(Collections.emptyList()))
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.computation())
    }
}