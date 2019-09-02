package krisanthe.exercise.mytvshows.screens.showList.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import krisanthe.exercise.mytvshows.model.ShowDetails
import krisanthe.exercise.mytvshows.utils.AppSchedulers
import timber.log.Timber

class ShowPresenter(
    private val schedulers: AppSchedulers,
    private val model: ShowModel,
    private val view: ShowView,
    private val subscriptions: CompositeDisposable
) {

    var showList = mutableListOf<ShowDetails>()

    fun onCreate() {
        subscriptions.add(clickItemEvent())
        subscriptions.add(addNewSearchValue())
    }

    fun onDestroy() {
        subscriptions.clear()
    }

    private fun clickItemEvent(): Disposable {
        return view.getClickObservable().subscribe { model.goToVideo() }
    }

    private fun addNewSearchValue(): Disposable {
        return view.getNewSearchValueObservable()
            .subscribe { newSearchValue -> subscriptions.add(getShowList(newSearchValue)) }
    }

    private fun getShowList(searchTerm: String): Disposable {
        return model.isNetworkAvailable().doOnNext { isNetworkAvailable ->
            if (!isNetworkAvailable) {
                Timber.e("No internet connection")
            }
        }
            .filter { true }
            .flatMap { model.provideShowList(searchTerm) }
            .subscribeOn(schedulers.internetScheduler())
            .observeOn(schedulers.androidThreadScheduler())
            .subscribe({ shows ->
                this.showList = shows as MutableList<ShowDetails>
                view.refreshAdapter(this.showList)
            }, { throwable ->
                view.showNoConnectionDialog()
                Timber.e(throwable, throwable.message)
            })
    }
}