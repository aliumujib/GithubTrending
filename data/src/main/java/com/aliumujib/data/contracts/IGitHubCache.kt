package com.aliumujib.data.contracts

import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Observable

/**
 * Created by aliumujib on 12/05/2018.
 */
interface IGitHubCache {

    fun getRepositories(filters: Map<String, String>): Observable<List<RepositoryEntity>>

    fun putRepositories(repos: List<RepositoryEntity>)

    fun clearRepositories()

    fun isEmpty() : Observable<Boolean>
}