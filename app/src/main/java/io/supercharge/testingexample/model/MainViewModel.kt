package io.supercharge.testingexample.model

import androidx.lifecycle.ViewModel
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.util.NoteUtil

class MainViewModel(
    noteStore: NoteStore,
    noteAction: NoteAction
) : ViewModel() {

    val noteList = noteStore.getNoteList()
        .map { list ->
            list.map { note ->
                note.copy(title = NoteUtil.getLocalizedNoteTitle(note.title))
            }
        }

    val sum = noteStore.getNoteList()
        .map(this::calculateSum)

    val refreshRunnable = Runnable { noteAction.refresh().subscribe() }

    private fun calculateSum(notes: List<Note>): Int {
        return notes.sumBy { note -> note.amount }
    }
}