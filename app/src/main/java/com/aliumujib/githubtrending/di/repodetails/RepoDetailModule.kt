package com.aliumujib.githubtrending.di.repodetails

import android.content.Context
import com.aliumujib.githubtrending.di.app.scopes.FragmentScope
import com.aliumujib.githubtrending.ui.main.MainActivity
import com.aliumujib.githubtrending.ui.main.MainNavigator
import com.aliumujib.githubtrending.ui.repodetails.RepoDetailPresenter
import com.aliumujib.githubtrending.ui.repodetails.RepoDetailsContracts
import com.aliumujib.githubtrending.ui.repodetails.RepoDetailsNavigator
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class RepoDetailModule(val context: Context) {


    @Provides
    @FragmentScope
    fun providesNavigator(): RepoDetailsContracts.Navigator {
        return RepoDetailsNavigator(context, MainNavigator(context as MainActivity))
    }

    @FragmentScope
    @Provides
    fun providesRepoDetailPresenter(navigator: RepoDetailsContracts.Navigator): RepoDetailPresenter {
        return RepoDetailPresenter(navigator)
    }

}