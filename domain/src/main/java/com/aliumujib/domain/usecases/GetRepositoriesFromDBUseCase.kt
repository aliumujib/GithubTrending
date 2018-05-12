package com.aliumujib.domain.usecases

import com.aliumujib.data.contracts.IGitHubCache
import com.aliumujib.domain.Params
import com.aliumujib.domain.base.BaseUseCase
import com.aliumujib.domain.entities.RepositoryModel
import com.aliumujib.domain.mapper.RepositoryMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 12/05/2018.
 */

class GetRepositoriesFromDBUseCase @Inject constructor(private val gitHubCache: IGitHubCache,
                                                       private val repositoryMapper: RepositoryMapper) : BaseUseCase<List<RepositoryModel>>() {

    override fun getObservable(params: Params): Observable<List<RepositoryModel>> {
        return gitHubCache.getRepositories(params.getParameters() as Map<String, String>)
                .flatMap { data -> repositoryMapper.observable(data) }
    }


}