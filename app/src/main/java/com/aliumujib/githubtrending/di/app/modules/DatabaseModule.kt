package com.aliumujib.githubtrending.di.app.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.aliumujib.data.contracts.IGitHubCache
import com.aliumujib.data.contracts.IGitHubCloud
import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.data.repository.GitHubRepository
import com.aliumujib.data.repository.GithubCache
import com.aliumujib.data.repository.GithubCloud
import com.aliumujib.data.room.GithubDao
import com.aliumujib.data.room.GithubDatabase
import com.aliumujib.githubtrending.di.app.scopes.ApplicationScope
import com.pick2me.android.data.repositories.services.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by aliumujib on 12/05/2018.
 */

@Module
class DatabaseModule {

    @ApplicationScope
    @Provides
    fun provideRoomDatabase(context: Context): GithubDatabase {
        return Room.databaseBuilder(
                context,
                GithubDatabase::class.java,
                "GITHUB_DB").build()
    }


    @ApplicationScope
    @Provides
    fun providesGithubDao(githubDatabase: GithubDatabase): GithubDao {
        return githubDatabase.getGitHubDao()
    }


    @ApplicationScope
    @Provides
    fun provideGithubReposCache(githubDao: GithubDao): IGitHubCache {
        return GithubCache(githubDao)
    }


    @ApplicationScope
    @Provides
    fun provideRemoteRepositoryDataStore(apiService: ApiService): IGitHubCloud {
        return GithubCloud(apiService)
    }


    @Provides
    @ApplicationScope
    fun providesRepository(githubCache: GithubCache, githubCloud: GithubCloud): IGitHubRepository {
        return GitHubRepository(githubCache, githubCloud)
    }
}