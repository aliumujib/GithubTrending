package com.aliumujib.githubtrending.ui.repolist


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aliumujib.githubtrending.ApplicationClass
import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.base.BaseFragment
import com.aliumujib.githubtrending.di.repolist.DaggerRepoListComponent
import com.aliumujib.githubtrending.di.repolist.RepoListComponent
import com.aliumujib.githubtrending.model.Repository
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

    private lateinit var component: RepoListComponent

    override fun injectDependencies() {
        super.injectDependencies()

        component = DaggerRepoListComponent.builder()
                    .appComponent(ApplicationClass.getInstance().appComponent).build()
        component.inject(this)
    }

    override fun hideLoading() {
        super.hideLoading()
        progress.visibility = View.GONE
    }

    override fun showLoading() {
        super.showLoading()
        progress.visibility = View.VISIBLE
    }


    override val layoutId: Int
        get() = R.layout.fragment_repo_list

    private lateinit var adapter: RepoAdapter

    override fun setData(data: MutableList<Repository>) {
        adapter.publishChanges(data)
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
        recyclerview.layoutManager = LinearLayoutManager(context)
        adapter = RepoAdapter({ getPresenter()?.retry() })
        recyclerview.adapter = adapter
    }


}
