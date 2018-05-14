package com.aliumujib.data.repository

import com.aliumujib.constants.NetworkState
import com.aliumujib.data.contracts.IGitHubCache
import com.aliumujib.data.contracts.IGitHubCloud
import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by aliumujib on 12/05/2018.
 */
class GitHubRepository constructor(private val githubCache: IGitHubCache, private val githubCloud: IGitHubCloud) : IGitHubRepository {
    var publishSubject : PublishSubject<NetworkState> = PublishSubject.create<NetworkState>()

    override fun networkState(): Observable<NetworkState> {
       return publishSubject
    }

    override fun loadMoreRepositories(filters: Map<String, String>) {
        loadReposFromCloud(false, filters)
    }

    override fun refreshRepositories(filters: Map<String, String>) {
        loadReposFromCloud(true, filters)
    }

    fun loadReposFromCloud(clearDB: Boolean, filters: Map<String, String>){
        publishSubject.onNext(NetworkState.LOADING)
        githubCloud.fetchRepositories(filters).subscribe({ data ->
            publishSubject.onNext(NetworkState.LOADED)

            if(clearDB){
               githubCache.clearRepositories()
           }
            githubCache.putRepositories(data)
        }, { t: Throwable? ->
            t?.printStackTrace()
            if(t!=null){
                publishSubject.onNext(NetworkState.error(t.message))
            }
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