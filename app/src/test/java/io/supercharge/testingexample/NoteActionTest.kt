package io.supercharge.testingexample

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.api.NoteApi
import io.supercharge.testingexample.domain.repository.NoteRepository
import io.supercharge.testingexample.threading.TestSchedulers
import net.lachlanmckee.timberjunit.TimberTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteActionTest {

    @Rule
    @JvmField
    val timberTestRule: TimberTestRule = TimberTestRule.logAllWhenTestFails()

    private val schedulers = TestSchedulers()

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
    fun `refresh calls notes api and saves the result into the notes repository`() {
        // given the server has one note
        val downloadedList = listOf(DEFAULT_NOTE)

        every { mockApi.downloadNotes() } returns Single.just(downloadedList)
        every { mockRepository.saveNotes(any()) } returns Completable.complete()

        val action = createNoteAction()

        // when the notes are refreshed
        val testSubscriber = action.refresh().test()

        // then the note list is received and saved to the repository
        testSubscriber.assertComplete()

        verifyOrder {
            mockApi.downloadNotes()
            mockRepository.saveNotes(downloadedList)
        }
    }

    private fun createNoteAction(): NoteAction = NoteService(schedulers, mockRepository, mockApi)
}
