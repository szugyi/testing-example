package io.supercharge.testingexample.domain.api

import io.reactivex.Single
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.threading.threadName
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

class NoteApi
@Inject internal constructor() {

    fun downloadNotes(): Single<List<Note>> {
        return Single.fromCallable {
            Timber.i("Loading notes on $threadName")

            Thread.sleep(1000)

            generateNotes()
        }
    }

    private fun generateNotes(): List<Note> {
        val notes = mutableListOf<Note>()

        for (i in 1..20) {
            notes.add(Note(Random.nextLong(), "Note: $i", Random.nextInt(10, 30)))
        }

        return notes
    }
}