package com.aliumujib.data.repository

import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 12/05/2018.
 */
class GitHubRepository @Inject constructor(private val githubCache: GithubCache) : IGitHubRepository {

    override fun fetchRepositories(filters: Map<String, String>): Observable<List<RepositoryEntity>> {
        return githubCache.getRepositories(filters)
    }

}