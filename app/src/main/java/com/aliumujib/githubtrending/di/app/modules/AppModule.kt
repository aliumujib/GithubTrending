package com.aliumujib.githubtrending.di.app.modules

import android.content.Context
import com.aliumujib.githubtrending.di.app.scopes.ApplicationScope
import com.aliumujib.githubtrending.utils.ImageLoader
import com.aliumujib.githubtrending.utils.PicassoImageLoader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by aliumujib on 12/05/2018.
 */

@Module
class AppModule constructor(context: Context){

    private val appContext = context.applicationContext

    @ApplicationScope
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @ApplicationScope
    @Provides
    fun provideImageLoader(): ImageLoader {
        return PicassoImageLoader(Picasso.get())
    }
}