package com.aliumujib.githubtrending.di.repolist

import com.aliumujib.githubtrending.di.app.AppComponent
import com.aliumujib.githubtrending.ui.repolist.RepoListFragment
import com.aliumujib.githubtrending.di.app.scopes.FragmentScope
import dagger.Component

/**
 * Created by aliumujib on 14/05/2018.
 */

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = arrayOf(RepoListModule::class))
interface RepoListComponent{

    fun inject(fragment: RepoListFragment)

}