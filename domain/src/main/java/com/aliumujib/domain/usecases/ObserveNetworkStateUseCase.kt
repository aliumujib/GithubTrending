package com.aliumujib.domain.usecases

import com.aliumujib.constants.NetworkState
import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.data.repository.GitHubRepository
import com.aliumujib.domain.Params
import com.aliumujib.domain.base.BaseUseCase
import io.reactivex.Observable
import io.reactivex.observers.DefaultObserver

/**
 * Created by aliumujib on 14/05/2018.
 */

class ObserveNetworkStateUseCase(private val gitHubRepository: IGitHubRepository) : BaseUseCase<NetworkState>() {

    override fun getObservable(params: Params): Observable<NetworkState> {
        return gitHubRepository.networkState()
    }

}