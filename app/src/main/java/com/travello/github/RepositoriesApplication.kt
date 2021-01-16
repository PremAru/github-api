package com.travello.github

import android.app.Application
import com.travello.github.di.AppComponent
import com.travello.github.di.DaggerAppComponent
import timber.log.Timber

class RepositoriesApplication : Application(){

    val appComponent: AppComponent by lazy { initalizeAppComponent() }

    private fun initalizeAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}