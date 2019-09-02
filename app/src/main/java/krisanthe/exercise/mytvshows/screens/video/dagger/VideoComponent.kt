package krisanthe.exercise.mytvshows.screens.video.dagger

import dagger.Component
import krisanthe.exercise.mytvshows.screens.video.VideoActivity

@Component(modules = [ExoPlayerModule::class])
interface VideoComponent {
    fun inject(videoActivity: VideoActivity)
}
