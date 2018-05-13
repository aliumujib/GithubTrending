package com.aliumujib.githubtrending.ui.repolist.paging

import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.aliumujib.constants.Constants
import com.aliumujib.data.contracts.IGitHubCache
import com.aliumujib.data.contracts.IGitHubCloud
import com.aliumujib.domain.Params
import com.aliumujib.domain.base.DefaultObserver
import com.aliumujib.githubtrending.model.RepositoryEntity
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * Created by aliumujib on 13/05/2018.
 */

/**
 * This boundary callback gets notified when user reaches to the edges of the list such that the
 * database cannot provide any more data.
 * <p>
 * The boundary callback might be called multiple times for the same direction so it does its own
 * rate limiting using the PagingRequestHelper class.
 */
class GithubRepositoryBoundaryCallback @Inject constructor(
        private val subredditName: String,
        private val webservice: IGitHubCloud,
        private val cache: IGitHubCache,
        private val ioExecutor: Executor,
        private val params: Params,
        private val networkPageSize: Int)
    : PagedList.BoundaryCallback<RepositoryEntity>() {

    val helper = PagingRequestHelper(ioExecutor)
    val networkState = helper.createStatusLiveData()

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            webservice.fetchRepositories(params.getParameters() as Map<String, String>)
                    .subscribe(createWebserviceCallback(it))
        }
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: RepositoryEntity) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            params.putString(Constants.FILTERS_CONSTANTS.ORDER, Constants.FILTERS_CONSTANTS.ORDER_TYPE_DESC)

            webservice.fetchRepositories(
                    params.getParameters() as Map<String, String>)
                    .subscribe(createWebserviceCallback(it))
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: RepositoryEntity) {
        // ignored, since we only ever append to what's in the DB
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : CloudDataObserver {
        return CloudDataObserver(it, cache)
    }


    inner class CloudDataObserver(private var pagingRequestHelperCallBack: PagingRequestHelper.Request.Callback, private var cache: IGitHubCache) : DefaultObserver<List<RepositoryEntity>>() {

        override fun onNext(list: List<RepositoryEntity>) {
            super.onNext(list)
            insertItemsIntoDb(list)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            pagingRequestHelperCallBack.recordFailure(exception)
        }

        /**
         * every time it gets new items, boundary callback simply inserts them into the database and
         * paging library takes care of refreshing the list if necessary.
         */
        private fun insertItemsIntoDb(response: List<RepositoryEntity>) {
            ioExecutor.execute {
                cache.putRepositories(response)
                //handleResponse(subredditName, response.body())
                pagingRequestHelperCallBack.recordSuccess()
            }
        }
    }

}