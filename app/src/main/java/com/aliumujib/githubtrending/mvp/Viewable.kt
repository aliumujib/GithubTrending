package com.aliumujib.githubtrending.mvp

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by aliumujib on 12/05/2018.
 */

/**
 * Android contract for every MVP View
 */
interface Viewable {

    /**
     * Sets the Title of the Screen
     */
    fun setTitle(@StringRes resource: Int)

    /**
     * Sets the Title of the Screen
     */
    fun setTitle(msg: CharSequence)

    /**
     * Every Viewable must have a error message system
     */
    fun displayError(message: String)

    /**
     * Every Viewable must have a error message system
     */
    fun displayError(messageId: Int)

    /**
     * Every Viewable must implement one show loading feature
     */
    fun showLoading()

    /**
     * Every Viewable must implement one hide loading feature
     */
    fun hideLoading()


    fun close()


    fun injectDependencies()

    fun attachToPresenter()

    fun detachFromPresenter()

    fun onLandscape()

    fun onPortrait()

    fun displayMessage(message: String)

    fun showNoNetwork()

}
