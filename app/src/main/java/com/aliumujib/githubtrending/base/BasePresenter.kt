package com.aliumujib.githubtrending.base

import com.aliumujib.githubtrending.mvp.Presentable
import com.aliumujib.githubtrending.mvp.Viewable

/**
 * Created by aliumujib on 12/05/2018.
 */


@Suppress("UNCHECKED_CAST")
abstract class BasePresenter<out T : Viewable> : Presentable {

    private var view: T? = null

    override val isViewAttached: Boolean
        get() = view != null


    open fun getView():T? {
        return view
    }

    override fun onStart() {
        // No-op by default
    }

    override fun onViewCreated() {
        // No-op by default
    }

    override fun onResume() {
        // No-op by default
    }

    override fun onPause() {
        // No-op by default
    }

    override fun onStop() {
        // No-op by default
    }

    fun attachView(viewable: Any) {
        this.view = viewable as T
    }

    override fun detachView() {
        this.view = null
    }

    @Throws(ViewNotAttachedException::class)
    override fun checkViewAttached() {
        if (!isViewAttached) throw ViewNotAttachedException()
    }

    class ViewNotAttachedException : RuntimeException("Call Presenter.attachView(BaseView) before asking for data")
}
