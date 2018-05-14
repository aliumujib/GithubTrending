package com.aliumujib.githubtrending.ui.repolist


import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.aliumujib.githubtrending.ApplicationClass
import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.base.BaseFragment
import com.aliumujib.githubtrending.di.repolist.DaggerRepoListComponent
import com.aliumujib.githubtrending.di.repolist.RepoListComponent
import com.aliumujib.githubtrending.di.repolist.RepoListModule
import com.aliumujib.githubtrending.model.NetworkState
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.ui.repolist.adapter.EndlessRecyclerViewScrollListener
import com.aliumujib.githubtrending.ui.repolist.adapter.RepoAdapter
import com.aliumujib.githubtrending.utils.ImageLoader
import com.aliumujib.githubtrending.utils.PicassoImageLoader
import com.squareup.picasso.Picasso
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

    private var imageLoader: ImageLoader = PicassoImageLoader(Picasso.get())

    override fun injectDependencies() {
        super.injectDependencies()

        component = DaggerRepoListComponent.builder()
                .appComponent(ApplicationClass.getInstance().appComponent)
                .repoListModule(RepoListModule(context!!))
                .build()
        component.inject(this)
    }

    override fun hideLoading() {
        super.hideLoading()
        progress.visibility = View.GONE
        swipe_refresh.isRefreshing = false
        adapter.setNetworkState(NetworkState.LOADED)
    }

    override fun showLoading() {
        super.showLoading()
        progress.visibility = View.VISIBLE
        swipe_refresh.isRefreshing = true
        adapter.setNetworkState(NetworkState.LOADING)
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
        initToolbar()
        initSwipeToRefresh()
    }

    private fun initSwipeToRefresh() {
        swipe_refresh.setOnRefreshListener {
            getPresenter()?.refresh()
        }
    }

    override fun showErrorView() {
        adapter.setNetworkState(NetworkState.error("Failed to load"))
    }

    override fun showEmptyView() {

    }

    @Inject
    override fun injectPresenter(presenter: RepoListPresenter) {
        super.injectPresenter(presenter)

    }


    private fun initAdapter() {
        var linearLayoutManager= LinearLayoutManager(context)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.addItemDecoration( DividerItemDecoration(context, LinearLayout.VERTICAL))
        adapter = RepoAdapter({
            repository ->
            getPresenter()?.gotoDetailsScreen(repository!!)
        },{ getPresenter()?.retry() }, imageLoader)
        recyclerview.adapter = adapter

        var scrollListener : EndlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                adapter.setNetworkState(NetworkState.LOADING)
                getPresenter()?.loadMore(adapter.itemCount)
            }

        }

        recyclerview.addOnScrollListener(scrollListener)
    }

    private fun initToolbar(){
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.title = "Trending"
    }


}
