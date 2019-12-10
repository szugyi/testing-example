package io.supercharge.testingexample

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Flowable
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.api.NoteApi
import io.supercharge.testingexample.domain.repository.NoteRepository
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.threading.RxSchedulers
import io.supercharge.testingexample.threading.TestSchedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class NoteStoreTest {

    private val schedulers = RxSchedulers()

    @MockK
    private lateinit var mockRepository: NoteRepository

    @MockK
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
        // given
        every { mockRepository.getNoteList() } returns Flowable.just(listOf(DEFAULT_NOTE))

        val noteStore = createNoteStore()

        // when
        val testSubscriber = noteStore.getNoteList().test()

        // then
        testSubscriber
            .assertNoErrors()
            .assertValue(listOf(DEFAULT_NOTE))

        verify { mockRepository.getNoteList() }
    }

    private fun createNoteStore(): NoteStore = NoteService(schedulers, mockRepository, mockApi)
}
