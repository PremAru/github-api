package com.travello.github.reposlist

import com.travello.github.R
import com.travello.github.base.BasePresenter
import com.travello.github.di.ActivityScope
import com.travello.github.model.Repositories
import com.travello.github.model.Repository
import com.travello.github.service.RepositoriesService
import com.travello.github.utils.Constants
import com.travello.github.utils.Constants.DAYSAGO
import com.travello.github.utils.Constants.ORDERPARAM
import com.travello.github.utils.Constants.SORTPARAM
import com.travello.github.utils.Constants.STARTING_PAGE_INDEX
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ActivityScope
class RepositoriesListPresenter @Inject constructor(repositoriesService: RepositoriesService) : BasePresenter<RepositoriesListView>() {

    private val repositoriesService: RepositoriesService = repositoriesService
    internal var repositoryList = mutableListOf<Repository>()
    internal var nextPageNumber: Int = STARTING_PAGE_INDEX
    private var isRequestInProgress: Boolean = false

    fun getRepositories() {
        isRequestInProgress = true
        repositoriesService.getRepositories(
            repositoryCreatedDaysAgo(DAYSAGO),
            SORTPARAM,
            ORDERPARAM,
            nextPageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<Repositories>(){
                override fun onComplete() {
                    Timber.i("Repositories list complete block executed")
                    isRequestInProgress = false
                }

                override fun onNext(repositories: Repositories) {
                    Timber.i("Repositories list success response received")
                    repositories?.let {
                        nextPageNumber ++
                        repositoryList.addAll(it.repositoryList)
                        getView().updateAdapter()
                    }
                }

                override fun onError(e: Throwable?) {
                    Timber.e("Repositories list error response received $e")
                    getView().displayError(Constants.DEFAULT_ERROR_MESSAGE)
                }
            })
    }


    fun repositoryCreatedDaysAgo(daysAgo: Int): String {
        val date = Calendar.getInstance()
        date.add(Calendar.DATE, -daysAgo)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = sdf.format(date.time)
        return "created:$formattedDate"
    }

    fun getRepositorytPosistion(position: Int): Repository? {
        Timber.d("Repositories list adapter at posistion $position")
        return repositoryList?.get(position)
    }

    fun scrolled(lastVisibleItemPosistion: Int, childCount: Int, itemCount: Int) {
        if (lastVisibleItemPosistion + childCount >= itemCount && !isRequestInProgress) {
            getRepositories()
        }

    }


}