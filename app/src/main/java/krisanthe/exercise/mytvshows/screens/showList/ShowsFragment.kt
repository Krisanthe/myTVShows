package krisanthe.exercise.mytvshows.screens.showList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import krisanthe.exercise.mytvshows.application.AppController
import krisanthe.exercise.mytvshows.screens.showList.core.ShowPresenter
import krisanthe.exercise.mytvshows.screens.showList.core.ShowView
import krisanthe.exercise.mytvshows.screens.showList.dagger.DaggerShowsComponent
import krisanthe.exercise.mytvshows.screens.showList.dagger.ShowsModule
import krisanthe.exercise.mytvshows.screens.video.VideoActivity
import javax.inject.Inject

class ShowsFragment : Fragment() {

    @Inject
    lateinit var presenter: ShowPresenter

    @Inject
    lateinit var view: ShowView

    override fun onCreate(saveInstantState: Bundle?) {
        super.onCreate(saveInstantState)

        DaggerShowsComponent.builder()
            .appComponent(AppController.appComponent)
            .showsModule(ShowsModule(this))
            .build()
            .inject(this)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view.view()
    }

    fun goToVideo() {
        val i = Intent(activity, VideoActivity::class.java)
        startActivity(i)
    }
}