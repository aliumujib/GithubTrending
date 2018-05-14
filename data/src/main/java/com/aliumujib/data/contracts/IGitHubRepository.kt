package com.aliumujib.data.contracts

import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Observable

/**
 * Created by aliumujib on 12/05/2018.
 */
interface IGitHubRepository {

    fun fetchRepositories(filters: Map<String, String>): Observable<List<RepositoryEntity>>

    fun refreshRepositories(filters: Map<String, String>)

    fun loadMoreRepositories(filters: Map<String, String>)

}