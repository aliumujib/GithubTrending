package com.aliumujib.githubtrending.ui.repolist

import com.aliumujib.constants.Constants
import com.aliumujib.domain.Params
import com.aliumujib.domain.base.DefaultObserver
import com.aliumujib.domain.entities.RepositoryModel
import com.aliumujib.domain.usecases.GetRepositoriesFromDBUseCase
import com.aliumujib.githubtrending.RepositoryModelMapper
import com.aliumujib.githubtrending.base.BasePresenter
import com.aliumujib.githubtrending.model.Repository

/**
 * Created by aliumujib on 12/05/2018.
 */
class RepoListPresenter(private var getRepositoriesFromDBUseCase: GetRepositoriesFromDBUseCase,
                        var repositoryModelMapper: RepositoryModelMapper = RepositoryModelMapper()) : BasePresenter<RepoListContracts.View>(),
                        RepoListContracts.Presenter {

    override fun refresh() {

        //https://api.github.com/search/repositories?q=android+language:java+language:kotlin&sort=stars&order=desc

        var params: Params = Params.create()
        params.putString(Constants.FILTERS_CONSTANTS.ORDER, Constants.FILTERS_CONSTANTS.ORDER_TYPE_DESC)
        params.putString(Constants.FILTERS_CONSTANTS.QUERY, "android+language:java+language:kotlin")
        params.putString(Constants.FILTERS_CONSTANTS.SORT, Constants.FILTERS_CONSTANTS.SORT_TYPE_STARS)
       // params.putString(Constants.FILTERS_CONSTANTS.LANGUAGE, "java")

        getRepositoriesFromDBUseCase.refresh(params)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        this.refresh()

        getRepositoriesFromDBUseCase.execute(RepositoryCacheObserver(), Params.EMPTY)
    }

    override fun retry() {

    }

    override fun onGetRepoSuccess(data: MutableList<Repository>) {
        getView()?.hideLoading()
        getView()?.setData(data)
    }

    override fun onGetDataFailure(exception: Throwable) {
        getView()?.hideLoading()
        getView()?.displayError(exception.localizedMessage)
    }


    inner class RepositoryCacheObserver : DefaultObserver<List<RepositoryModel>>() {
        override fun onNext(t: List<RepositoryModel>) {
            super.onNext(t)
            onGetRepoSuccess(repositoryModelMapper.mapFrom(t))
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onGetDataFailure(exception)
        }
    }

}