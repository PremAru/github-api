package com.travello.github.reposlist

interface RepositoriesListView {
    fun updateAdapter()
    fun displayError(errorMessage: String)
}
