package io.supercharge.testingexample.main

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.model.MainViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class MainViewModelTest {

    @RelaxedMockK
    private lateinit var mockNoteService: NoteService

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun loadNoteListFromStore_EmptyResults() {
        // given
        every { mockNoteService.getNoteList() } returns Flowable.just(emptyList())

        val viewModel = MainViewModel(mockNoteService)

        // when
        val testSubscriber = viewModel.noteList.test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue { list -> list.isEmpty() }
    }

    @Test
    fun loadNoteListFromStore_NonEmptyResults() {
        // given
        every { mockNoteService.getNoteList() } returns Flowable.just(getNotes())

        val viewModel = MainViewModel(mockNoteService)

        // when
        val testSubscriber = viewModel.noteList.test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue { list -> !list.isEmpty() }
    }

    @Test
    fun loadNoteListFromStore_CorrectValueCalculated() {
        // given
        every { mockNoteService.getNoteList() } returns Flowable.just(getNotes())

        val viewModel = MainViewModel(mockNoteService)

        // when
        val testSubscriber = viewModel.noteList.test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue { list -> viewModel.calculate(list) == 50 }
    }

    @Test
    fun callRefreshFromAction_Complete() {
        // given
        every { mockNoteService.refresh() } returns Completable.complete()

        val viewModel = MainViewModel(mockNoteService)

        // when
        viewModel.refreshRunnable.run()

        // then
        verify { mockNoteService.refresh() }
    }

    private fun getNotes(): List<Note> {
        val noteList = ArrayList<Note>()

        noteList.add(Note(0, "Title1", 20))
        noteList.add(Note(1, "Title2", 30))

        return noteList
    }
}