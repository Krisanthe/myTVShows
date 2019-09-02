package krisanthe.exercise.mytvshows.screens.showList.core

import io.reactivex.Observable
import krisanthe.exercise.mytvshows.api.ShowApi
import krisanthe.exercise.mytvshows.model.ShowDetails
import krisanthe.exercise.mytvshows.screens.showList.ShowsFragment
import krisanthe.exercise.mytvshows.utils.isNetworkAvailableObservable

class ShowModel(val context: ShowsFragment, private val api: ShowApi) {

    fun provideShowList(searchTerm: String): Observable<MutableList<ShowDetails>> {
        return api.getShows(searchTerm)
    }

    fun isNetworkAvailable(): Observable<Boolean> {
        return context.activity!!.isNetworkAvailableObservable()
    }

    fun goToVideo() {
        context.goToVideo()
    }
}