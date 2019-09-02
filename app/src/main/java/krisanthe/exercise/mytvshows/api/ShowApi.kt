package krisanthe.exercise.mytvshows.api

import io.reactivex.Observable
import krisanthe.exercise.mytvshows.model.ShowDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowApi {

    @GET("search/shows")
    fun getShows(@Query("q") showTitle: String): Observable<MutableList<ShowDetails>>
}
