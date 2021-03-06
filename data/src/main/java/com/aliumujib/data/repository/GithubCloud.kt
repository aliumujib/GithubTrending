package com.aliumujib.data.repository

import com.aliumujib.data.contracts.IGitHubCloud
import com.aliumujib.githubtrending.model.RepositoryEntity
import com.pick2me.android.data.repositories.services.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by aliumujib on 12/05/2018.
 */

class GithubCloud @Inject constructor(private val apiService: ApiService) : IGitHubCloud {

    override fun fetchRepositories(filters: Map<String, String>): Observable<List<RepositoryEntity>> {
        return apiService.searchRepositories(filters)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { data->
             Observable.just(data.items)
        }
    }

}