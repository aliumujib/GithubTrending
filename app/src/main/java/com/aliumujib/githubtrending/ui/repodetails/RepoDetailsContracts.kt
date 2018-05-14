package com.aliumujib.githubtrending.ui.repodetails

import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.mvp.Viewable

/**
 * Created by aliumujib on 12/05/2018.
 */

interface RepoDetailsContracts {

    interface View : Viewable {

        fun showData(data: Repository)


    }


    interface Presenter {

        fun gotoRepositoryWebPage()

        fun setData(data: Repository)

        fun closeView()
    }


    interface Navigator {

        fun openRepositoryWebPage(repository: Repository)

        fun closeDetails()
    }
}