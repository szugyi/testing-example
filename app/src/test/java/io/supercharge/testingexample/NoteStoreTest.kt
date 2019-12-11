package io.supercharge.testingexample

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.api.NoteApi
import io.supercharge.testingexample.domain.repository.NoteRepository
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.threading.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class NoteStoreTest {

    private lateinit var schedulers: Schedulers

    private lateinit var mockRepository: NoteRepository

    private lateinit var mockApi: NoteApi

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getNoteList returns the notes from the note repository`() {
        // given the repository contains one note

        // when the note list is accessed


        // then we receive the list with one item
    }

    private fun createNoteStore(): NoteStore = NoteService(schedulers, mockRepository, mockApi)
}
