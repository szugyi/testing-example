package io.supercharge.testingexample

import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.threading.TestSchedulers
import org.junit.Before
import org.junit.Test

class NoteServiceUnitTest {

    private val schedulers = TestSchedulers()

    private lateinit var noteService: NoteService

    @Before
    fun setup() {
        noteService = NoteService(schedulers)
    }

    @Test
    fun refresh_Perfect_Complete() {
        noteService.refresh()
            .test()
            .assertComplete()
    }

    @Test
    fun getNotes_Perfect_NoErrors() {
        noteService.getNoteList()
            .test()
            .assertNoErrors()
            .assertNotComplete()
    }
}
