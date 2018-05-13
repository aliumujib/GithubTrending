package com.aliumujib.domain.mapper

import com.aliumujib.domain.base.Mapper
import com.aliumujib.domain.entities.RepositoryModel
import com.aliumujib.githubtrending.model.RepositoryEntity
import javax.inject.Inject

/**
 * Created by aliumujib on 12/05/2018.
 */
class RepositoryMapper @Inject constructor(private val userMapper: UserMapper) : Mapper<RepositoryEntity, RepositoryModel>() {

    override fun mapFrom(from: RepositoryEntity): RepositoryModel {
        return RepositoryModel(id = from.id,
                repoName = from.repoName,
                repoFullName = from.repoFullName,
                repoDescription = from.repoDescription,
                user = userMapper.mapFrom(from.user!!),
                starsCount = from.starsCount,
                language = from.language
        )
    }
}