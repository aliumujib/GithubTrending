package com.aliumujib.githubtrending.ui.repolist

import android.arch.paging.PagedList
import com.aliumujib.constants.NetworkState
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.model.RepositoryEntity
import com.aliumujib.githubtrending.mvp.Viewable

/**
 * Created by aliumujib on 12/05/2018.
 */

interface RepoListContracts {

    interface View : Viewable {
        fun setData(data: MutableList<Repository>)

        fun showEmptyView()

        fun showErrorView(error: String)

        //fun setPagedData(data: PagedList<Repository>)
    }


    interface Presenter {

        fun onGetRepoSuccess(data: MutableList<Repository>)

        fun onGetDataFailure(exception: Throwable)

        fun retry()

        fun refresh()

        fun loadMore(skipCount: Int)

        fun gotoDetailsScreen(repository: Repository)
        fun onNetworkStateChanged(networkState: NetworkState)
    }


     interface Navigator {

        fun openRepository(repository: Repository)
    }
}