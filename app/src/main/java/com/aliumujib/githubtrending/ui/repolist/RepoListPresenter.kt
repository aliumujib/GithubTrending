package com.aliumujib.githubtrending.ui.repolist

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.aliumujib.data.repository.GithubCache
import com.aliumujib.domain.base.DefaultObserver
import com.aliumujib.githubtrending.base.BasePresenter
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.model.RepositoryEntity
import com.google.gson.annotations.Until
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * Created by aliumujib on 12/05/2018.
 */
class RepoListPresenter(var githubCache: GithubCache, ) : BasePresenter<RepoListContracts.View>(), RepoListContracts.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()

        val concertList: Flowable<PagedList<Repository>> = RxPagedListBuilder(
                githubCache.getRepositories(HashMap()),
                /* page size */ 50
        ).buildFlowable(BackpressureStrategy.LATEST)
    }

    override fun retry() {

    }

    override fun onGetRepoSuccess(data: MutableList<Repository>) {

    }

    override fun onGetDataFailure() {

    }


    inner class RepositoryCacheObserver : DefaultObserver<PagedList<Repository>>() {

    }

}