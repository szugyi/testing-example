package io.supercharge.testingexample.domain.action

import io.reactivex.Completable

interface NoteAction {
    fun refresh(): Completable
}