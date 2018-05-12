package com.aliumujib.githubtrending.ui.repolist

import com.aliumujib.githubtrending.model.RepositoryEntity
import com.aliumujib.githubtrending.mvp.Viewable

/**
 * Created by aliumujib on 12/05/2018.
 */

interface RepoListContracts{

    interface View : Viewable {
        fun setData(data: MutableList<RepositoryEntity>)
    }



    interface Presenter{

        fun onGetRepoSuccess(data: MutableList<RepositoryEntity>)

        fun onGetDataFailure()

    }
}