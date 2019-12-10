package io.supercharge.testingexample.main

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import io.reactivex.Flowable
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.model.MainViewModel
import io.supercharge.testingexample.util.NoteUtil
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class MainViewModelTest {

    @MockK
    private lateinit var mockNoteAction: NoteAction

    @MockK
    private lateinit var mockNoteStore: NoteStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { mockNoteStore.getNoteList() } returns Flowable.just(getNotes())
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `note list is empty if the note store does not have any note`() {
        // given
        every { mockNoteStore.getNoteList() } returns Flowable.just(emptyList())

        val viewModel = createViewModel()

        // when
        val testSubscriber = viewModel.noteList.test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue { list -> list.isEmpty() }
    }

    @Test
    fun `note list contains the notes returned by the note store`() {
        // given
        mockkObject(NoteUtil)

        every { NoteUtil.getLocalizedNoteTitle(any()) } returns ""

        val viewModel = createViewModel()

        // when
        val testSubscriber = viewModel.noteList.test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue { list -> list.isNotEmpty() }
    }

    @Test
    fun `sum returns the sum of all note amounts`() {
        // given
        val viewModel = createViewModel()

        // when
        val testSubscriber = viewModel.sum.test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue(50)
    }

    @Test
    fun `refresh action is called when refresh runnable is run`() {
        // given
        every { mockNoteAction.refresh() } returns Completable.complete()

        val viewModel = createViewModel()

        // when
        viewModel.refreshRunnable.run()

        // then
        verify { mockNoteAction.refresh() }
    }

    private fun createViewModel() = MainViewModel(mockNoteStore, mockNoteAction)

    private fun getNotes(): List<Note> {
        val noteList = ArrayList<Note>()

        noteList.add(Note(0, "Title1", 20))
        noteList.add(Note(1, "Title2", 30))

        return noteList
    }
}