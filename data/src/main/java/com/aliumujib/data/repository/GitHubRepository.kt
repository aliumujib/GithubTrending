package com.aliumujib.data.repository

import com.aliumujib.data.contracts.IGitHubCache
import com.aliumujib.data.contracts.IGitHubCloud
import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 12/05/2018.
 */
class GitHubRepository  constructor(private val githubCache: IGitHubCache, private val githubCloud: IGitHubCloud) : IGitHubRepository {

    override fun refreshRepositories(filters: Map<String, String>) {
        githubCloud.fetchRepositories(filters).subscribe({ data ->
            githubCache.clearRepositories()
            githubCache.putRepositories(data)
        }, {
            t: Throwable? ->
            t?.printStackTrace()
        })
    }

    override fun fetchRepositories(filters: Map<String, String>): Observable<List<RepositoryEntity>> {
        return githubCache.getRepositories(filters)
    }

}