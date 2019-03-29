package io.supercharge.testingexample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.supercharge.testingexample.di.DaggerAppComponent

class TestingExampleApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

}