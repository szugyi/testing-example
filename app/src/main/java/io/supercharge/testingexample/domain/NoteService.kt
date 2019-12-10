package io.supercharge.testingexample.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.api.NoteApi
import io.supercharge.testingexample.domain.model.Note
import io.supercharge.testingexample.domain.repository.NoteRepository
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.threading.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteService
@Inject internal constructor(
    private val schedulers: Schedulers,
    private val noteRepository: NoteRepository,
    private val noteApi: NoteApi
) : NoteStore, NoteAction {

    override fun refresh(): Completable =
        noteApi.downloadNotes()
            .doOnSuccess { Timber.d("We could map api models to data models here") }
            .doOnError { Timber.d("Network error handling maybe?") }
            .subscribeOn(schedulers.io())
            .flatMapCompletable(noteRepository::saveNotes)

    override fun getNoteList(): Flowable<List<Note>> = noteRepository.getNoteList()
}