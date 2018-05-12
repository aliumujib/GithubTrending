package com.aliumujib.githubtrending.ui.repolist


import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.base.BaseFragment
<<<<<<< HEAD
import com.aliumujib.githubtrending.model.Repository
=======
>>>>>>> b8a409618f2277284511ab58c00096fbee42ea2a
import com.aliumujib.githubtrending.model.RepositoryEntity


class RepoListFragment : BaseFragment<RepoListPresenter>(), RepoListContracts.View {

    override val layoutId: Int
        get() = R.layout.fragment_repo_list

    override fun setData(data: MutableList<RepositoryEntity>) {

    }

}
