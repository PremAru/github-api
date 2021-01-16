package com.travello.github.di

import com.travello.github.reposlist.RepositoriesListComponent
import dagger.Module

@Module(
    subcomponents = [
        RepositoriesListComponent::class
    ]
)
class AppSubcomponents