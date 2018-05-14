package com.aliumujib.githubtrending

import android.app.Application
import com.aliumujib.githubtrending.di.app.AppComponent
import com.aliumujib.githubtrending.di.app.DaggerAppComponent
import com.aliumujib.githubtrending.di.app.modules.AppModule
import com.squareup.leakcanary.LeakCanary

/**
 * Created by aliumujib on 14/05/2018.
 */

class ApplicationClass : Application() {

     var appComponent: AppComponent? = null


    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        applicationClass = this
        initDependencies()
    }


    private fun initDependencies() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .build()

            appComponent?.inject(this)
        }
    }

    companion object {
        lateinit var applicationClass: ApplicationClass

        fun getInstance(): ApplicationClass = applicationClass
    }

}