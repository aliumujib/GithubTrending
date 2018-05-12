package com.aliumujib.domain.mapper

import com.aliumujib.data.model.UserEntity
import com.aliumujib.domain.base.Mapper
import com.aliumujib.domain.entities.UserModel

/**
 * Created by aliumujib on 12/05/2018.
 */

class UserMapper : Mapper<UserEntity, UserModel>() {

    override fun mapFrom(from: UserEntity): UserModel {
        return UserModel(name = from.name, imageUrl = from.imageUrl)
    }

}