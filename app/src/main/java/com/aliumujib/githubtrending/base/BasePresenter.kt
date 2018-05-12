package com.aliumujib.githubtrending.base

import com.aliumujib.githubtrending.mvp.Presentable
import com.aliumujib.githubtrending.mvp.Viewable

/**
 * Created by aliumujib on 12/05/2018.
 */


abstract class BasePresenter<T : Viewable> : Presentable {

    var view: T? = null
        private set


    override val isViewAttached: Boolean
        get() = view != null

    override fun onStart() {
        // No-op by default
    }

    override  fun onViewCreated() {
        // No-op by default
    }

    override  fun onResume() {
        // No-op by default
    }

    override  fun onPause() {
        // No-op by default
    }

    override  fun onStop() {
        // No-op by default
    }

    fun attachView(viewable: T) {
        this.view = viewable
    }

    override  fun detachView() {
        this.view = null
    }

    @Throws(ViewNotAttachedException::class)
    override  fun checkViewAttached() {
        if (!isViewAttached) throw ViewNotAttachedException()
    }

    class ViewNotAttachedException : RuntimeException("Call Presenter.attachView(BaseView) before asking for data")
}
