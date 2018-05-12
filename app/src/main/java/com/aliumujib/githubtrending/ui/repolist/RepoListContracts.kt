package com.aliumujib.githubtrending.ui.repolist

import com.aliumujib.githubtrending.model.Repository

/**
 * Created by aliumujib on 12/05/2018.
 */

interface RepoListContracts{

    interface RepoListView{
        fun setData(data: MutableList<Repository>)
    }



    interface RepoListPresenter{

        fun onGetRepoSuccess(data: MutableList<Repository>)

        fun onGetDataFailure()

    }
}