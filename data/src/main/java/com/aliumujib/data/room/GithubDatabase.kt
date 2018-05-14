package com.aliumujib.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.aliumujib.data.model.UserEntity
import com.aliumujib.githubtrending.model.RepositoryEntity


/**
 * Created by aliumujib on 20/01/2018.
 */
@TypeConverters(GithubTypeConverters::class)
@Database(entities = [(RepositoryEntity::class), (UserEntity::class)], version = 1, exportSchema = true)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun getGitHubDao(): GithubDao
}