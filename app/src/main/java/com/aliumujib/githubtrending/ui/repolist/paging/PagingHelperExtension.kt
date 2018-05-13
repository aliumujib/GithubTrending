package com.aliumujib.githubtrending.ui.repolist.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.aliumujib.githubtrending.model.NetworkState

/**
 * Created by aliumujib on 13/05/2018.
 */

private fun getErrorMessage(report: PagingRequestHelper.StatusReport): String {
    return PagingRequestHelper.RequestType.values().mapNotNull {
        report.getErrorFor(it)?.message
    }.first()
}

fun PagingRequestHelper.createStatusLiveData(): LiveData<NetworkState> {
    val liveData = MutableLiveData<NetworkState>()
    addListener { report ->
        when {
            report.hasRunning() -> liveData.postValue(NetworkState.LOADING)
            report.hasError() -> liveData.postValue(
                    NetworkState.error(getErrorMessage(report)))
            else -> liveData.postValue(NetworkState.LOADED)
        }
    }
    return liveData
}
