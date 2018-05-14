package com.aliumujib.githubtrending.ui.repodetails


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliumujib.githubtrending.ApplicationClass

import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.base.BaseFragment
import com.aliumujib.githubtrending.di.repodetails.DaggerRepoDetailComponent
import com.aliumujib.githubtrending.di.repodetails.RepoDetailComponent
import com.aliumujib.githubtrending.di.repodetails.RepoDetailModule
import com.aliumujib.githubtrending.di.repolist.DaggerRepoListComponent
import com.aliumujib.githubtrending.di.repolist.RepoListModule
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.ui.repolist.RepoListFragment
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import javax.inject.Inject
import android.view.KeyEvent.KEYCODE_BACK


/**
 * A simple [Fragment] subclass.
 */
class RepoDetailFragment : BaseFragment<RepoDetailPresenter>(), RepoDetailsContracts.View {

    override fun showData(data: Repository) {
        repo_fullname.text = data.repoFullName
        repo_description.text = data.repoDescription
        toolbar.title = data.repoName
        language.text = data.language
        star_count.text = "${data.starsCount} stars"
        repo_fullname.setOnClickListener {
            getPresenter()?.gotoRepositoryWebPage()
        }

    }

    override val layoutId: Int
        get() = R.layout.fragment_repo_detail


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.navigationIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_close_white_24dp)
        getPresenter()?.setData(arguments!!.getParcelable(ARG))

        getView()?.isFocusableInTouchMode = true
        getView()?.requestFocus()
        getView()?.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                getPresenter()?.closeView()
                true
            } else false
        }

        toolbar.setNavigationOnClickListener {
            getPresenter()?.closeView()
        }
    }

    @Inject
    override fun injectPresenter(presenter: RepoDetailPresenter) {
        super.injectPresenter(presenter)
    }

    lateinit var component: RepoDetailComponent

    override fun injectDependencies() {
        super.injectDependencies()

        component = DaggerRepoDetailComponent.builder()
                .appComponent(ApplicationClass.getInstance().appComponent)
                .repoDetailModule(RepoDetailModule(context!!))
                .build()

        component.inject(this)

    }

    companion object {
        val TAG = "RepoDetailFragment"
        val ARG = "RepoDetailFragment_ARG"

        fun newInstance(repository: Repository): RepoDetailFragment {
            var fragment = RepoDetailFragment()
            var bundle = Bundle()
            bundle.putParcelable(ARG, repository)
            fragment.arguments = bundle
            return fragment
        }
    }

}
