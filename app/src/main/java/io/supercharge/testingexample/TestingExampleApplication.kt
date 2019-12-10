package io.supercharge.testingexample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.supercharge.testingexample.di.DaggerAppComponent
import timber.log.Timber

class TestingExampleApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

}