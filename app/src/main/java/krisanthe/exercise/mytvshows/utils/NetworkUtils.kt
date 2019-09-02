package krisanthe.exercise.mytvshows.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Observable

fun Activity.isNetworkAvailableObservable(): Observable<Boolean> {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return Observable.just(activeNetworkInfo != null && activeNetworkInfo.isConnected)
}