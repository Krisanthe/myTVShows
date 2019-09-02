package krisanthe.exercise.mytvshows.application

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(private val context: Context) {

    @AppScope
    @Provides
    fun providesContext(): Context {
        return context
    }
}