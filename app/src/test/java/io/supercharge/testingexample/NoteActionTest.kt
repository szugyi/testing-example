package io.supercharge.testingexample

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.api.NoteApi
import io.supercharge.testingexample.domain.repository.NoteRepository
import io.supercharge.testingexample.threading.Schedulers
import net.lachlanmckee.timberjunit.TimberTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteActionTest {

    @Rule
    @JvmField
    val timberTestRule: TimberTestRule = TimberTestRule.logAllWhenTestFails()

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
    fun `refresh calls notes api and saves the result into the notes repository`() {
        // given the server has one note

        // when the notes are refreshed

        // then the note list is received and saved to the repository
    }

    private fun createNoteAction(): NoteAction = NoteService(schedulers, mockRepository, mockApi)
}
