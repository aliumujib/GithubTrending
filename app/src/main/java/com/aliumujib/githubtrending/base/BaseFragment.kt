package com.aliumujib.githubtrending.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliumujib.githubtrending.mvp.Viewable
import com.hannesdorfmann.fragmentargs.FragmentArgs

/**
 * Created by aliumujib on 12/05/2018.
 */

abstract class BaseFragment<in T : BasePresenter< Viewable>> : Fragment(), Viewable {

    /**
     * {@inheritDoc}
     */
    private var presenter: T? = null

    protected abstract val layoutId: Int


    private val appCompatActivity: AppCompatActivity
        get() = activity as AppCompatActivity

    /**
     * {@inheritDoc}
     */
    override fun setTitle(@StringRes resource: Int) {
        setTitle(getString(resource))
    }

    /**
     * {@inheritDoc}
     */
    override fun setTitle(msg: CharSequence) {
        activity?.title = msg
    }

    /**
     * {@inheritDoc}
     */
    override fun onStart() {
        super.onStart()
        if (presenter != null) {
            presenter!!.onStart()
        }
    }


    /**
     * {@inheritDoc}
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
        retainInstance = true
    }

    /**
     * {@inheritDoc}
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)

        //ButterKnife.setDebug(true);

        injectDependencies()

        if (presenter != null) {
            presenter?.attachView(this)
        }

        return view
    }

    /**
     * {@inheritDoc}
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (presenter != null) {
            presenter!!.onViewCreated()
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun onDestroyView() {
        if (presenter != null) {
            presenter!!.detachView()
        }
        super.onDestroyView()
    }

    /**
     * {@inheritDoc}
     */
    override fun onStop() {
        if (presenter != null) {
            presenter!!.onStop()
        }
        super.onStop()
    }

    /**
     * {@inheritDoc}
     */
    override fun onDestroy() {
        presenter = null
        super.onDestroy()
    }

    /**
     * {@inheritDoc}
     */
    override fun displayError(message: String) {
        val rootContent = view?.findViewById<View>(android.R.id.content)
        if (rootContent != null) {
            Snackbar.make(rootContent, message, Snackbar.LENGTH_LONG).show()
        }
    }


    /**
     * {@inheritDoc}
     */
    override fun displayError(messageId: Int) {
        displayError(getString(messageId))
    }

    /**
     * {@inheritDoc}
     */
    override fun showLoading() {
        // no-op by default
    }

    /**
     * {@inheritDoc}
     */
    override fun hideLoading() {
        // no-op by default
    }

    override fun injectDependencies() {

    }

    override fun attachToPresenter() {

    }

    override fun detachFromPresenter() {

    }

    override fun onLandscape() {

    }

    override fun onPortrait() {

    }

    override fun displayMessage(message: String) {

    }

    override fun showNoNetwork() {

    }

    override fun close() {
        appCompatActivity.finish()
    }

    /**
     * Every fragment should be able to set its presenter class
     */
    fun injectPresenter(presenter: T) {
        this.presenter = presenter
    }


    interface FragmentNavigator {
        fun changeFragment(fragmentTag: String,
                           sharedElement: Pair<View, String>?)
    }

}
