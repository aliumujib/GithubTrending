package com.aliumujib.githubtrending.ui.repolist

import android.content.Context
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.ui.main.IMainNavigator
import com.aliumujib.githubtrending.ui.main.MainNavigator
import com.aliumujib.githubtrending.ui.repodetails.DetailCustomTab

/**
 * Created by aliumujib on 14/05/2018.
 */

class RepoListNavigator(private var mainNavigator: IMainNavigator) : RepoListContracts.Navigator {


    override fun openRepository(repository: Repository) {
        mainNavigator.goToRepository(repository)
    }

}