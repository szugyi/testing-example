package io.supercharge.testingexample.di

import dagger.Module
import dagger.Provides
import io.supercharge.testingexample.threading.RxSchedulers
import io.supercharge.testingexample.threading.Schedulers
import javax.inject.Singleton

@Module
class ThreadModule {

    @Provides
    @Singleton
    fun provideSchedulers(): Schedulers = RxSchedulers()
}