package io.supercharge.testingexample.main

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.model.MainViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class MainViewModelTest {

    private lateinit var mockNoteAction: NoteAction

    private lateinit var mockNoteStore: NoteStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `note list is empty if the note store does not have any note`() {
        // given an empty note store

        // when the note list is bound

        // then the note list contains no element
    }

    @Test
    fun `note list contains the notes returned by the note store`() {
        // given a note store with multiple notes

        // when the note list is bound

        // then the note list is not empty
    }

    @Test
    fun `sum returns the sum of all note amounts`() {
        // given a note store with multiple notes

        // when the sum value is bound()

        // then the sum of all the elements is received
    }

    @Test
    fun `refresh action is called when refresh runnable is run`() {
        // given an initialized viewModel

        // when the refresh runnable is invoked

        // then the refresh action is triggered
    }

    private fun createViewModel() = MainViewModel(mockNoteStore, mockNoteAction)

    private fun getNotes(): List<Note> {
        val noteList = ArrayList<Note>()

        noteList.add(Note(0, "Title1", 20))
        noteList.add(Note(1, "Title2", 30))

        return noteList
    }
}