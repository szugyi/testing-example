package io.supercharge.testingexample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.supercharge.testingexample.domain.NoteService
import io.supercharge.testingexample.model.MainViewModel

@Module
class ViewModelModule {
    @Provides
    internal fun provideMainViewModelFactory(noteService: NoteService) : ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(noteService) as T
            }
        }
    }
}