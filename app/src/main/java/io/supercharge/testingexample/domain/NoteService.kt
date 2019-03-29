package io.supercharge.testingexample.domain

import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.threading.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class NoteService @Inject internal constructor(
    private val schedulers: Schedulers
) : NoteStore, NoteAction {

    private val notesProcessor: FlowableProcessor<List<Note>> = BehaviorProcessor.create<List<Note>>()

    override fun refresh(): Completable = Completable.fromAction { notesProcessor.onNext(generateNotes()) }

    override fun getNoteList(): Flowable<List<Note>> {
        return Flowable.create<List<Note>>({ source ->
            val disposable = notesProcessor
                .subscribe({ source.onNext(it) }, { source.onError(it) })

            source.setCancellable { disposable.dispose() }
        }, BackpressureStrategy.BUFFER)
            .onErrorResumeNext(Flowable.just(Collections.emptyList()))
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    private fun generateNotes(): List<Note> {
        val notes = ArrayList<Note>()

        for (i in 1..20) {
            notes.add(Note(Random.nextLong(), "Note: ".plus(i), Random.nextInt(10, 30)))
        }

        return notes
    }
}