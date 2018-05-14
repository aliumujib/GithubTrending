package com.aliumujib.domain.usecases

import com.aliumujib.data.contracts.IGitHubCache
import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.data.repository.GitHubRepository
import com.aliumujib.data.repository.GithubCloud
import com.aliumujib.domain.Params
import com.aliumujib.domain.base.BaseUseCase
import com.aliumujib.domain.entities.RepositoryModel
import com.aliumujib.domain.mapper.RepositoryMapper
import com.aliumujib.domain.mapper.UserMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 12/05/2018.
 */

class GetRepositoriesFromDBUseCase @Inject constructor(private val gitHubRepository: IGitHubRepository,
                                                       private val repositoryMapper: RepositoryMapper = RepositoryMapper(UserMapper())) : BaseUseCase<List<RepositoryModel>>() {

    override fun getObservable(params: Params): Observable<List<RepositoryModel>> {
        return gitHubRepository.fetchRepositories(params.getParameters() as Map<String, String>)
                .flatMap { data -> repositoryMapper.observable(data) }
    }

    fun refresh(params: Params){
        gitHubRepository.refreshRepositories(params.getParameters() as Map<String, String>)
    }


}