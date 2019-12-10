package io.supercharge.testingexample.model

import androidx.lifecycle.ViewModel
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.domain.store.NoteStore

class MainViewModel(noteStore: NoteStore, noteAction: NoteAction) : ViewModel() {

    val noteList = noteStore.getNoteList()

    val refreshRunnable = Runnable { noteAction.refresh().subscribe() }

    fun calculate(notes: List<Note>): Int {
        return notes.sumBy { note -> note.amount }
    }
}