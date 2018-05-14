package com.aliumujib.data.room

import android.arch.persistence.room.*
import com.aliumujib.githubtrending.model.RepositoryEntity
import io.reactivex.Flowable

/**
 * Created by aliumujib on 20/01/2018.
 */
@Dao
interface GithubDao {

    @Query("SELECT * FROM REPOSITORIES")
    fun getFavorites(): Flowable<List<RepositoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movies: List<RepositoryEntity>)

    @Query("DELETE FROM REPOSITORIES")
    fun clear()

}