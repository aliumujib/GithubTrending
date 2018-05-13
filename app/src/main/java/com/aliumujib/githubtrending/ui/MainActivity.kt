package com.aliumujib.githubtrending.ui

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.view.View
import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.ui.repolist.RepoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment(RepoListFragment.TAG, null)
    }

    fun findFragment(TAG: String): Fragment {
        var fragment: Fragment = Fragment()
        if (supportFragmentManager.findFragmentByTag(TAG) == null) {
            if (TAG == RepoListFragment.TAG) {
                fragment = RepoListFragment.newInstance()
            }
        } else {
            fragment = supportFragmentManager.findFragmentByTag(TAG)
        }

        return fragment
    }


    fun initFragment(fragmentTag: String,
                     sharedElement: Pair<View, String>?) {
        //currentFragmentTag = fragmentTag
        val fragment = findFragment(fragmentTag)

        val fragmentTransaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_frame, fragment)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (sharedElement != null) {
                fragmentTransaction.addSharedElement(sharedElement.first, sharedElement.second)
                fragment.sharedElementEnterTransition = window.sharedElementEnterTransition
                fragment.sharedElementReturnTransition = window.sharedElementReturnTransition
            }
        }

        fragmentTransaction.commit()
    }
}
