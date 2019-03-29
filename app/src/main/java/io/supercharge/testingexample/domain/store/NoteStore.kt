package io.supercharge.testingexample.domain.store

import io.reactivex.Flowable
import io.supercharge.testingexample.domain.model.Note

interface NoteStore {
    fun getNoteList(): Flowable<List<Note>>
}