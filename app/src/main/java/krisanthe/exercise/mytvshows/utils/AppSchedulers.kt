package krisanthe.exercise.mytvshows.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class AppSchedulers {

    fun internetScheduler(): Scheduler {
        return Schedulers.from(Executors.newCachedThreadPool())
    }

    fun androidThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}