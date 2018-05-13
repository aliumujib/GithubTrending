package com.aliumujib.githubtrending

import com.aliumujib.domain.base.Mapper
import com.aliumujib.domain.entities.RepositoryModel
import com.aliumujib.domain.entities.UserModel
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.model.User


class RepositoryModelMapper : Mapper<RepositoryModel, Repository>() {

    override fun mapFrom(from: RepositoryModel): Repository {
        val fromUser: UserModel = from.user

        return Repository(id = from.id,
                repoFullName = from.repoFullName,
                repoName = from.repoName,
                repoDescription = from.repoDescription,
                language = from.language,
                starsCount = from.starsCount,
                user = User(name = fromUser.name, imageUrl = fromUser.imageUrl))
    }


}