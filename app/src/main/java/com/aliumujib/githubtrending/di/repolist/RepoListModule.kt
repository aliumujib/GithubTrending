package com.aliumujib.githubtrending.di.repolist

import android.content.Context
import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.domain.usecases.GetRepositoriesFromDBUseCase
import com.aliumujib.domain.usecases.ObserveNetworkStateUseCase
import com.aliumujib.githubtrending.ui.repolist.RepoListPresenter
import com.aliumujib.githubtrending.di.app.scopes.FragmentScope
import com.aliumujib.githubtrending.ui.main.MainActivity
import com.aliumujib.githubtrending.ui.main.MainNavigator
import com.aliumujib.githubtrending.ui.repolist.RepoListContracts
import com.aliumujib.githubtrending.ui.repolist.RepoListNavigator
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class RepoListModule(val context: Context) {


    @Provides
    @FragmentScope
    fun providesNavigator(): RepoListContracts.Navigator {
        return RepoListNavigator(MainNavigator(context as MainActivity))
    }

    @FragmentScope
    @Provides
    fun providesRepoListPresenter(getRepositoriesFromDBUseCase: GetRepositoriesFromDBUseCase, netwFromDBUseCase: ObserveNetworkStateUseCase, navigator: RepoListContracts.Navigator): RepoListPresenter {
        return RepoListPresenter(getRepositoriesFromDBUseCase, navigator = navigator, networkStateDBUseCase = netwFromDBUseCase)
    }

    @FragmentScope
    @Provides
    fun providesObserveNetworkStateUseCaseUseCase(gitHubRepository: IGitHubRepository): ObserveNetworkStateUseCase {
        return ObserveNetworkStateUseCase(gitHubRepository = gitHubRepository)
    }

    @FragmentScope
    @Provides
    fun providesGetDataForRepoUseCase(gitHubRepository: IGitHubRepository): GetRepositoriesFromDBUseCase {
        return GetRepositoriesFromDBUseCase(gitHubRepository = gitHubRepository)
    }
}