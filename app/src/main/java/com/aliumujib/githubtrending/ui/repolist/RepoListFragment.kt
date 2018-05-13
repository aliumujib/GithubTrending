package com.aliumujib.githubtrending.ui.repolist


import android.arch.paging.PagedList
import android.os.Bundle
import android.view.View
import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.base.BaseFragment
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.ui.repolist.adapter.PagedRepoAdapter
import com.aliumujib.githubtrending.ui.repolist.adapter.RepoAdapter
import kotlinx.android.synthetic.main.fragment_repo_list.*
import javax.inject.Inject


class RepoListFragment : BaseFragment<RepoListPresenter>(), RepoListContracts.View {


    companion object {
        val TAG = "RepoListFragment"

        fun newInstance(): RepoListFragment {
            var repoListFragment = RepoListFragment()
            var bundle = Bundle();
            repoListFragment.arguments = bundle
            return repoListFragment
        }
    }

    override fun injectDependencies() {
        super.injectDependencies()
    }



    override val layoutId: Int
        get() = R.layout.fragment_repo_list

    private lateinit var adapter: RepoAdapter

    override fun setData(data: MutableList<Repository>) {

    }

    override fun setPagedData(data: PagedList<Repository>) {
       // adapter.submitList(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    @Inject
    override fun injectPresenter(presenter: RepoListPresenter) {
        super.injectPresenter(presenter)
    }

    private fun initAdapter() {
        adapter = RepoAdapter({ getPresenter()?.retry() })
        recyclerview.adapter = adapter

//        model.posts.observe(this, Observer<PagedList<RedditPost>> {
//        })
//        model.networkState.observe(this, Observer {
//            adapter.setNetworkState(it)
//        })
    }



}
