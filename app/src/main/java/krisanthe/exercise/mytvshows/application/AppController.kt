package krisanthe.exercise.mytvshows.application

import android.app.Application
import krisanthe.exercise.mytvshows.BuildConfig
import timber.log.Timber

class AppController : Application() {

    companion object {
        var appComponent: AppComponent? = null
    }

    private lateinit var instance: AppController

    override fun onCreate() {
        super.onCreate()
        instance = this

        initializeLogger()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appContextModule(AppContextModule(this))
            .build()
    }

    private fun initializeLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String, message: String, t: Throwable) {
                    //TODO Realise version
                }
            })
        }
    }
}