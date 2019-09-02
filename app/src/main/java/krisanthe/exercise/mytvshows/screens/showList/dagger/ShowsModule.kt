package krisanthe.exercise.mytvshows.screens.showList.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import krisanthe.exercise.mytvshows.api.ShowApi
import krisanthe.exercise.mytvshows.screens.showList.ShowsFragment
import krisanthe.exercise.mytvshows.screens.showList.core.ShowModel
import krisanthe.exercise.mytvshows.screens.showList.core.ShowPresenter
import krisanthe.exercise.mytvshows.screens.showList.core.ShowView
import krisanthe.exercise.mytvshows.utils.AppSchedulers

@Module
class ShowsModule(val context: ShowsFragment) {

    @ShowsScope
    @Provides
    fun provideContext() : ShowsFragment {
        return context
    }

    @ShowsScope
    @Provides
    fun provideView(context: ShowsFragment) : ShowView {
        return ShowView(context)
    }

    @ShowsScope
    @Provides
    fun provideModel(api: ShowApi) : ShowModel {
        return ShowModel(context, api)
    }

    @ShowsScope
    @Provides
    fun providePresenter(schedulers: AppSchedulers, model: ShowModel, view: ShowView) : ShowPresenter {
        val subscriptions = CompositeDisposable()
        return ShowPresenter(schedulers, model, view, subscriptions)
    }
}