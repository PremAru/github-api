package com.travello.github.di

import android.content.Context
import com.travello.github.interceptor.ForceCacheInterceptor
import com.travello.github.reposlist.RepositoriesListComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppSubcomponents::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun repositoriesListComponent() : RepositoriesListComponent.Factory

    fun forceCacheInterceptor() : ForceCacheInterceptor

}