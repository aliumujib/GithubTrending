package com.aliumujib.data.repository

import com.aliumujib.data.contracts.IGitHubCache
import com.aliumujib.data.contracts.IGitHubCloud
import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Observable

/**
 * Created by aliumujib on 12/05/2018.
 */
class GitHubRepository constructor(private val githubCache: IGitHubCache, private val githubCloud: IGitHubCloud) : IGitHubRepository {
    override fun loadMoreRepositories(filters: Map<String, String>) {
        loadReposFromCloud(false, filters)
    }

    override fun refreshRepositories(filters: Map<String, String>) {
        loadReposFromCloud(true, filters)
    }

    fun loadReposFromCloud(clearDB: Boolean, filters: Map<String, String>){
        githubCloud.fetchRepositories(filters).subscribe({ data ->
           if(clearDB){
               githubCache.clearRepositories()
           }
            githubCache.putRepositories(data)
        }, { t: Throwable? ->
            t?.printStackTrace()
        })
    }

    override fun fetchRepositories(filters: Map<String, String>): Observable<List<RepositoryEntity>> {
        githubCache.isEmpty().subscribe({
            empty->
            if (empty) {
                refreshRepositories(filters)
            }
        })

        return githubCache.getRepositories(filters)
    }


}