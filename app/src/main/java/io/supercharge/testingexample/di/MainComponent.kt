package io.supercharge.testingexample.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import io.supercharge.testingexample.view.MainActivity

@Subcomponent(modules = [ViewModelModule::class])
interface MainComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
