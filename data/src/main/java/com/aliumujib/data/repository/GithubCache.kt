package com.aliumujib.data.repository

import com.aliumujib.data.contracts.IGitHubCache
import com.aliumujib.githubtrending.model.RepositoryEntity
import com.aliumujib.data.room.GithubDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import javax.inject.Inject

/**
 * Created by aliumujib on 12/05/2018.
 */

class GithubCache @Inject constructor(private val dao: GithubDao) : IGitHubCache {

    override fun clearRepositories() {
        doAsync {
            dao.clear()
        }
    }

    override fun putRepositories(repos: List<RepositoryEntity>) {
        doAsync {
            dao.saveAllMovies(repos)
        }
    }

    override fun getRepositories(filters: Map<String, String>): Observable<List<RepositoryEntity>> {
        return dao.getFavorites().toObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}