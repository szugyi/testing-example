package io.supercharge.testingexample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.supercharge.testingexample.view.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}
