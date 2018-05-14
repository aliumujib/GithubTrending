package com.aliumujib.githubtrending.ui.repolist


import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.aliumujib.githubtrending.ApplicationClass
import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.base.BaseFragment
import com.aliumujib.githubtrending.di.repolist.DaggerRepoListComponent
import com.aliumujib.githubtrending.di.repolist.RepoListComponent
import com.aliumujib.githubtrending.model.Repository
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
                .appComponent(ApplicationClass.getInstance().appComponent).build()
        component.inject(this)
    }

    override fun hideLoading() {
        super.hideLoading()
        progress.visibility = View.GONE
        swipe_refresh.isRefreshing = false
    }

    override fun showLoading() {
        super.showLoading()
        progress.visibility = View.VISIBLE
        swipe_refresh.isRefreshing = true
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

    }

    override fun showEmptyView() {

    }

    @Inject
    override fun injectPresenter(presenter: RepoListPresenter) {
        super.injectPresenter(presenter)

    }


    private fun initAdapter() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.addItemDecoration( DividerItemDecoration(context, LinearLayout.VERTICAL))
        adapter = RepoAdapter({ getPresenter()?.retry() }, imageLoader)
        recyclerview.adapter = adapter
    }

    private fun initToolbar(){
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.title = "Trending"
    }


}
