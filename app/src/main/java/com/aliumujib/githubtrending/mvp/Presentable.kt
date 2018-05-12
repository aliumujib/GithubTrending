package com.aliumujib.githubtrending.mvp

/**
 * Created by aliumujib on 12/05/2018.
 */

/**
 * Android contract for every MVP Presenter
 */
interface Presentable {


    /**
     * Every Presentable must be able to access to its attached View
     *
     * @return V Viewable
     */


    val isViewAttached: Boolean

    /**
     * Every Presentable must implement onStart state
     */
    fun onStart()

    /**
     * Every Presentable must implement onViewCreated state
     */
    fun onViewCreated()

    /**
     * Every Presentable must implement onResume state
     */
    fun onResume()


    /**
     * Every Presentable must implement onPause state
     */
    fun onPause()


    /**
     * Every Presentable must implement onStop state
     */
    fun onStop()



    /**
     * Every Presentable must detach its Viewable when stopped
     */
    fun detachView()


    /**
     * Every Presentable must know if its Viewable is attached
     */
    fun checkViewAttached()


}
