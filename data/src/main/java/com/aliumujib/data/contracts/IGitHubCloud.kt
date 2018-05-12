package com.aliumujib.data.contracts

import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Observable

/**
 * Created by aliumujib on 12/05/2018.
 */
interface IGitHubCloud {

    fun fetchRepositories(filters: Map<String, String>): Observable<List<RepositoryEntity>>

}