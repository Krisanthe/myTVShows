package krisanthe.exercise.mytvshows.screens.video.dagger

import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import dagger.Module
import dagger.Provides
import krisanthe.exercise.mytvshows.screens.video.VideoActivity

@Module
class ExoPlayerModule(val context: VideoActivity) {

    @Provides
    fun provideRendererFactory(): DefaultRenderersFactory {
        return DefaultRenderersFactory(context)
    }

    @Provides
    fun provideBandwidthMeter(): DefaultBandwidthMeter {
        return DefaultBandwidthMeter()
    }

    @Provides
    fun provideTrackSelector(bandwidthMeter: DefaultBandwidthMeter): AdaptiveTrackSelection.Factory {
        return AdaptiveTrackSelection.Factory(bandwidthMeter)
    }

    @Provides
    fun provideExoPlayer(
        rendererFactory: DefaultRenderersFactory,
        trackSelectorFactory: AdaptiveTrackSelection.Factory
    ): SimpleExoPlayer {
        return ExoPlayerFactory.newSimpleInstance(
            rendererFactory,
            DefaultTrackSelector(trackSelectorFactory),
            DefaultLoadControl()
        )
    }
}