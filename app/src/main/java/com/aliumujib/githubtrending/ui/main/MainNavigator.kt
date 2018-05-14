package com.aliumujib.githubtrending.ui.main

import android.support.v4.app.FragmentTransaction
import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.ui.repodetails.RepoDetailFragment
import com.aliumujib.githubtrending.ui.repolist.RepoListFragment

/**
 * Created by aliumujib on 14/05/2018.
 */

interface IMainNavigator {
    fun goToRepository(repository: Repository)

    fun closeDetails(): Boolean

    fun goToList()
}

class MainNavigator(private var mainActivity: MainActivity) : IMainNavigator {

    override fun goToRepository(repository: Repository) {
        val fragment = RepoDetailFragment.newInstance(repository)
        mainActivity.supportFragmentManager.beginTransaction().add(R.id.fragment_frame, fragment, RepoDetailFragment.TAG).commitNow()
    }


    override fun closeDetails(): Boolean {
        val details = mainActivity.supportFragmentManager.findFragmentByTag(RepoDetailFragment.TAG)
        if (details != null) {
            mainActivity.supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .remove(details)
                    .commitNow()
            return true
        }
        return false
    }


    override fun goToList() {
        val fragment = RepoListFragment.newInstance()

        val transaction = mainActivity.supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(R.id.fragment_frame, fragment).commitNow()
    }

}