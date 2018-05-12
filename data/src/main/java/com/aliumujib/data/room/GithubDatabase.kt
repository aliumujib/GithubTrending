package com.yossisegev.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.aliumujib.data.model.UserEntity
import com.aliumujib.githubtrending.model.RepositoryEntity


/**
 * Created by aliumujib on 20/01/2018.
 */
@Database(entities = arrayOf(RepositoryEntity::class, UserEntity::class), version = 1)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): GithubDao
}