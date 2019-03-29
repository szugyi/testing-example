package io.supercharge.testingexample.main

import io.reactivex.Completable
import io.reactivex.Flowable
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.model.MainViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    private lateinit var mockNoteService: NoteService

    @Test
    fun loadNoteListFromStore_EmptyResults() {
        // given
        given(mockNoteService.getNoteList()).willReturn(Flowable.just(Collections.emptyList()))

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
        given(mockNoteService.getNoteList()).willReturn(Flowable.just(getNotes()))

        val viewModel = MainViewModel(mockNoteService)

        // when
        val testSubscriber = viewModel.noteList.test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue { list -> !list.isEmpty() }
    }

    @Test
    fun callRefreshFromAction_Complete() {
        // given
        given(mockNoteService.refresh()).willReturn(Completable.complete())

        val viewModel = MainViewModel(mockNoteService)

        // when
        viewModel.refreshRunnable.run()

        // then
        then(mockNoteService).should().refresh()
    }

    @Test
    fun loadNoteListFromStore_CorrectValueCalculated() {
        // given
        given(mockNoteService.getNoteList()).willReturn(Flowable.just(getNotes()))

        val viewModel = MainViewModel(mockNoteService)

        // when
        val testSubscriber = viewModel.noteList.test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue { list -> viewModel.calculate(list) == 50 }
    }

    private fun getNotes(): List<Note> {
        val noteList = ArrayList<Note>()

        noteList.add(Note(0, "Title1", 20))
        noteList.add(Note(1, "Title2", 30))

        return noteList
    }
}