package com.yossisegev.data.db

import android.arch.persistence.room.*
import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Flowable

/**
 * Created by aliumujib on 20/01/2018.
 */
@Dao
interface GithubDao {

    @Query("SELECT * FROM movies")
    fun getFavorites(): Flowable<List<RepositoryEntity>>

}