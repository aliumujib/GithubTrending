package com.aliumujib.githubtrending.di.app.modules

import com.aliumujib.data.api.ApiClient
import com.aliumujib.githubtrending.di.app.scopes.ApplicationScope
import com.pick2me.android.data.repositories.services.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by aliumujib on 12/05/2018.
 */
@Module
class NetworkModule() {

    @ApplicationScope
    @Provides
    fun provideApiClient(): ApiClient {
        return ApiClient()
    }

    @ApplicationScope
    @Provides
    fun provideApi(apiClient: ApiClient): ApiService {
        return apiClient.service
    }

}
