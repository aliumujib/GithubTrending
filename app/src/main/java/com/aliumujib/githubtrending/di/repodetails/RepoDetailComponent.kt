package com.aliumujib.githubtrending.di.repodetails

import com.aliumujib.githubtrending.di.app.AppComponent
import com.aliumujib.githubtrending.di.app.scopes.FragmentScope
import com.aliumujib.githubtrending.di.repolist.RepoListModule
import com.aliumujib.githubtrending.ui.repodetails.RepoDetailFragment
import com.aliumujib.githubtrending.ui.repolist.RepoListFragment
import dagger.Component

/**
 * Created by aliumujib on 14/05/2018.
 */

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(RepoDetailModule::class)])
interface RepoDetailComponent {

    fun inject(fragment: RepoDetailFragment)

}