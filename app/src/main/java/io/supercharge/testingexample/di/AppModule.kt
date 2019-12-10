package io.supercharge.testingexample.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.threading.Schedulers
import javax.inject.Singleton

@Module(subcomponents = [MainComponent::class])
abstract class AppModule {

    @Binds
    abstract fun bindNoteAction(service: NoteService) : NoteAction

    @Binds
    abstract fun bindNoteStore(service: NoteService) : NoteStore
}
