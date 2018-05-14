package com.aliumujib.githubtrending.ui.repodetails

import android.content.Context
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.ui.main.MainNavigator

/**
 * Created by aliumujib on 14/05/2018.
 */

class RepoDetailsNavigator(var context: Context, private var mainNavigator: MainNavigator) : RepoDetailsContracts.Navigator {

    override fun closeDetails() {
        mainNavigator.closeDetails()
    }

    override fun openRepositoryWebPage(repository: Repository) {
        detailCustomTab.openTab(context, repository.webURL)
    }

    private var detailCustomTab: DetailCustomTab = DetailCustomTab()


}