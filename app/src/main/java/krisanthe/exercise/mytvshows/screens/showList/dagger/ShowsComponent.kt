package krisanthe.exercise.mytvshows.screens.showList.dagger

import dagger.Component
import krisanthe.exercise.mytvshows.application.AppComponent
import krisanthe.exercise.mytvshows.screens.showList.ShowsFragment

@ShowsScope
@Component(modules = [ShowsModule::class], dependencies = [AppComponent::class])
interface ShowsComponent {
    fun inject(showsFragment: ShowsFragment)
}