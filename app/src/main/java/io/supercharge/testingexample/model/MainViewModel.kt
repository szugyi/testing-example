package io.supercharge.testingexample.model

import androidx.lifecycle.ViewModel
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.model.Note

class MainViewModel(noteService: NoteService) : ViewModel() {

    val noteList = noteService.getNoteList()

    val refreshRunnable = Runnable { noteService.refresh().subscribe() }

    fun calculate(notes: List<Note>): Int {
        return notes.sumBy { note -> note.amount }
    }
}