package com.aliumujib.githubtrending.ui.repolist

import com.aliumujib.githubtrending.base.BasePresenter
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.mvp.Presentable

/**
 * Created by aliumujib on 12/05/2018.
 */
class RepoListPresenter : BasePresenter<RepoListContracts.View>(), RepoListContracts.Presenter {









    override fun onGetRepoSuccess(data: MutableList<Repository>) {

    }

    override fun onGetDataFailure() {
    }
}