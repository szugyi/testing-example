package io.supercharge.testingexample.di

import dagger.Module
import dagger.Provides
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.threading.Schedulers
import javax.inject.Singleton

@Module(subcomponents = [MainComponent::class])
open class AppModule {

    @Singleton
    @Provides
    internal fun provideNoteService(schedulers: Schedulers) : NoteService = NoteService(schedulers)
}
