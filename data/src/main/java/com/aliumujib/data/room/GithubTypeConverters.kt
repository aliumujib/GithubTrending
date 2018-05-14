package com.aliumujib.data.room

import android.arch.persistence.room.TypeConverter
import com.aliumujib.data.model.UserEntity
import java.util.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by aliumujib on 14/05/2018.
 */
class GithubTypeConverters {

    @TypeConverter
    fun fromString(value: String): UserEntity {
        val type = object : TypeToken<UserEntity>() {
        }.type
        return Gson().fromJson<UserEntity>(value, type)
    }

    @TypeConverter
    fun fromUser(list: UserEntity): String {
        val gSon = Gson()
        return gSon.toJson(list)
    }

}