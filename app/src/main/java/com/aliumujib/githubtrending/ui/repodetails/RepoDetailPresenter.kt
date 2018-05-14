package com.aliumujib.githubtrending.ui.repodetails

import com.aliumujib.githubtrending.base.BasePresenter
import com.aliumujib.githubtrending.model.Repository

/**
 * Created by aliumujib on 14/05/2018.
 */

class RepoDetailPresenter (var navigator: RepoDetailsContracts.Navigator) : BasePresenter<RepoDetailsContracts.View>(), RepoDetailsContracts.Presenter {

    override fun closeView() {
        navigator.closeDetails()
    }

    var repository:Repository? = null

    override fun setData(data: Repository) {
        repository = data
        getView()?.showData(data)
    }

    override fun gotoRepositoryWebPage() {
        navigator.openRepositoryWebPage(repository!!)
    }

}