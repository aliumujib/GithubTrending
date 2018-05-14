package com.aliumujib.githubtrending.di.app

import com.aliumujib.data.contracts.IGitHubRepository
import com.aliumujib.githubtrending.ApplicationClass
import com.aliumujib.githubtrending.di.app.modules.AppModule
import com.aliumujib.githubtrending.di.app.modules.DatabaseModule
import com.aliumujib.githubtrending.di.app.modules.NetworkModule
import com.aliumujib.githubtrending.di.app.scopes.ApplicationScope
import com.aliumujib.githubtrending.utils.ImageLoader
import dagger.Component

/**
 * Created by aliumujib on 14/05/2018.
 */


@ApplicationScope
@Component(modules = [
(AppModule::class),
(NetworkModule::class),
(DatabaseModule::class)
])

interface AppComponent {

    fun inject(app: ApplicationClass)

    fun repository(): IGitHubRepository

}