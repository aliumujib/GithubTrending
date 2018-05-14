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
                        var repositoryModelMapper: RepositoryModelMapper = RepositoryModelMapper(), val navigator: RepoListContracts.Navigator) : BasePresenter<RepoListContracts.View>(),
        RepoListContracts.Presenter {

    override fun gotoDetailsScreen(repository: Repository) {
        navigator.openRepository(repository = repository)
    }


    override fun loadMore(itemCount: Int) {
        var currentPage = itemCount / Constants.FILTERS_CONSTANTS.COUNT_PER_PAGE
        params.putInt(Constants.FILTERS_CONSTANTS.PAGE_NUMBER, currentPage + 1)
        getRepositoriesFromDBUseCase.loadMore(params)
    }


    var params: Params = Params.create()

    init {
        params.putString(Constants.FILTERS_CONSTANTS.ORDER, Constants.FILTERS_CONSTANTS.ORDER_TYPE_DESC)
        params.putString(Constants.FILTERS_CONSTANTS.QUERY, "android+language:java+language:kotlin")
        params.putString(Constants.FILTERS_CONSTANTS.SORT, Constants.FILTERS_CONSTANTS.SORT_TYPE_STARS)
        params.putInt(Constants.FILTERS_CONSTANTS.PER_PAGE, Constants.FILTERS_CONSTANTS.COUNT_PER_PAGE)
    }


    override fun onStop() {
        super.onStop()
        getRepositoriesFromDBUseCase.dispose()
    }

    override fun refresh() {
        params.putInt(Constants.FILTERS_CONSTANTS.PAGE_NUMBER, 1)
        getRepositoriesFromDBUseCase.refresh(params)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getRepositoriesFromDBUseCase.execute(RepositoryCacheObserver(), params)
    }

    override fun retry() {

    }

    override fun onGetRepoSuccess(data: MutableList<Repository>) {
        if (!data.isEmpty()) {
            getView()?.hideLoading()
            getView()?.setData(data)
        } else {
            getView()?.showEmptyView()
        }
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