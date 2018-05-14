package com.aliumujib.githubtrending.di.repolist

import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.data.repository.GitHubRepository
import com.aliumujib.domain.usecases.GetRepositoriesFromDBUseCase
import com.aliumujib.githubtrending.ui.repolist.RepoListPresenter
import com.aliumujib.githubtrending.di.app.scopes.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class RepoListModule {

    @FragmentScope
    @Provides
    fun providesRepoListPresenter(getRepositoriesFromDBUseCase: GetRepositoriesFromDBUseCase): RepoListPresenter {
        return RepoListPresenter(getRepositoriesFromDBUseCase)
    }

    @FragmentScope
    @Provides
    fun providesGetDataForRepoUseCase(gitHubRepository: IGitHubRepository): GetRepositoriesFromDBUseCase {
        return GetRepositoriesFromDBUseCase(gitHubRepository = gitHubRepository)
    }
}