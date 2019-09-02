package krisanthe.exercise.mytvshows.application

import dagger.Component
import krisanthe.exercise.mytvshows.api.ShowApi
import krisanthe.exercise.mytvshows.utils.AppSchedulers


@AppScope
@Component(modules = [AppContextModule::class, ApiServiceModule::class, ConnectionModule::class])
interface AppComponent {

    fun apiService(): ShowApi
    fun appSchedulers(): AppSchedulers
}