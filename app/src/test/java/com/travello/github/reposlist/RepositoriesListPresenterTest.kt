package com.travello.github.reposlist

import com.nhaarman.mockitokotlin2.*
import com.travello.github.model.Owner
import com.travello.github.model.Repositories
import com.travello.github.model.Repository
import com.travello.github.service.RepositoriesService
import com.travello.github.utils.Constants
import com.travello.github.utils.Constants.DEFAULT_ERROR_MESSAGE
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException


@RunWith(MockitoJUnitRunner::class)
class RepositoriesListPresenterTest {

    @Mock
    lateinit var repositoriesService: RepositoriesService

    @Mock
    lateinit var repositoryListView: RepositoriesListView

    lateinit var presenter: RepositoriesListPresenter

    private val userName = "userName"
    private val avatarUrl = "http://avatarurl"
    private var owner = Owner(userName, avatarUrl)
    private val description = "repoDesc"
    private val name = "repoName"
    private val totalCount = 123
    private val starCount = "23"


    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { trampoline() }

        presenter = RepositoriesListPresenter(repositoriesService)
        presenter.setView(repositoryListView)
    }

    @Test
    fun shouldMakeNetworkCallToFetchRepositories() {
        var repositories = createRepositoriesData()
        whenever(repositoriesService.getRepositories(any(),
                eq(Constants.SORTPARAM),
                eq(Constants.ORDERPARAM),
                any())).thenReturn(Observable.just(repositories))

        presenter.getRepositories()

        then(verify(repositoriesService).getRepositories(any(),
            eq(Constants.SORTPARAM),
            eq(Constants.ORDERPARAM),
            any()))


    }

    @Test
    fun shouldReturnSuccessResponseAndUpdateTheAdapter() {
        var repositories = createRepositoriesData()
        whenever(repositoriesService.getRepositories(any(),
            eq(Constants.SORTPARAM),
            eq(Constants.ORDERPARAM),
            any())).thenReturn(Observable.just(repositories))

        presenter.getRepositories()
        then(verify(presenter.getView()).displayLoadingIndicator())

        then(verify(presenter.getView()).updateAdapter())

        val actualRepositoryList = presenter.repositoryList
        val actualPageNumber = presenter.nextPageNumber

        then(assertThat(actualRepositoryList, `is`(repositories.repositoryList)))
        then(assertThat(actualPageNumber, `is`(2)))

        then(verify(presenter.getView()).hideLoadingIndicator())

    }


    @Test
    fun shouldNetworkCallFailAndDisplayError() {
        val errorMessage = "Something went wrong"
        whenever(repositoriesService.getRepositories(any(),
                eq(Constants.SORTPARAM),
                eq(Constants.ORDERPARAM),
                any())).thenReturn(Observable.error(IOException(errorMessage)))

        presenter.getRepositories()
        then(verify(presenter.getView()).displayLoadingIndicator())

        then(verify(presenter.getView()).displayError(DEFAULT_ERROR_MESSAGE))

        val actualPageNumber = presenter.nextPageNumber
        then(assertThat(actualPageNumber, `is`(1)))
        then(verify(presenter.getView()).hideLoadingIndicator())

    }



    private fun createRepositoriesData() : Repositories {

        var repositoryList = listOf<Repository>(
                Repository(name, description, starCount, owner)
        )
        var repositories = Repositories(totalCount, repositoryList)
        return repositories
    }

}