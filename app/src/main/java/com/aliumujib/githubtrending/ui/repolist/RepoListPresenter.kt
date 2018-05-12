package com.aliumujib.githubtrending.ui.repolist

import com.aliumujib.githubtrending.base.BasePresenter
import com.aliumujib.githubtrending.model.RepositoryEntity

/**
 * Created by aliumujib on 12/05/2018.
 */
class RepoListPresenter : BasePresenter<RepoListContracts.View>(), RepoListContracts.Presenter {









    override fun onGetRepoSuccess(data: MutableList<RepositoryEntity>) {

    }

    override fun onGetDataFailure() {
    }
}