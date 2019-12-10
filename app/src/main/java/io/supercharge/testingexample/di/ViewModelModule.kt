package io.supercharge.testingexample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.domain.action.NoteAction
import io.supercharge.testingexample.domain.store.NoteStore
import io.supercharge.testingexample.model.MainViewModel

@Module
class ViewModelModule {
    @Provides
    internal fun provideMainViewModelFactory(noteAction: NoteAction, noteStore: NoteStore) : ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(noteStore, noteAction) as T
            }
        }
    }
}