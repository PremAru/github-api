package com.travello.github.service

import com.travello.github.model.Repositories
import com.travello.github.utils.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesService {

    @GET(Constants.URL_REPOSITORY_LIST)
    fun getRepositories(
        @Query("q") queryParam: String,
        @Query("sort") sortParam: String,
        @Query("order") orderParam: String,
        @Query("page") pageParam: Int
    ) : Observable<Repositories>
}