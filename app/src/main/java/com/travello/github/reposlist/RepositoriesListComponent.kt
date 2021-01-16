package com.travello.github.reposlist

import com.travello.github.di.ActivityScope
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface RepositoriesListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RepositoriesListComponent
    }

    fun inject(activity: RepositoriesListActivity)

    fun inject(adapter: RepositoryAdapter)
}