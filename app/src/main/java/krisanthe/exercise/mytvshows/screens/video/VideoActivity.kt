package krisanthe.exercise.mytvshows.screens.video

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.a_video.*
import krisanthe.exercise.mytvshows.R
import krisanthe.exercise.mytvshows.screens.video.dagger.DaggerVideoComponent
import krisanthe.exercise.mytvshows.screens.video.dagger.ExoPlayerModule
import javax.inject.Inject


class VideoActivity : AppCompatActivity() {

    @Inject
    lateinit var simpleExoplayer: SimpleExoPlayer

    @Inject
    lateinit var bandwidthMeter: DefaultBandwidthMeter


    private val BASE_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    private val BASE_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:40.0) Gecko/20100101 Firefox/40.0"
    private var playbackPosition = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerVideoComponent.builder()
            .exoPlayerModule(ExoPlayerModule(this))
            .build().inject(this)


        setContentView(R.layout.a_video)
    }

    override fun onStart() {
        super.onStart()

        initializeExoplayer()
    }

    override fun onStop() {

        releaseExoplayer()
        super.onStop()
    }


    private fun initializeExoplayer() {
        prepareExoplayer()
        ep_video_view.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
    }

    private fun releaseExoplayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultHttpDataSourceFactory(BASE_AGENT, bandwidthMeter)
        return  ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri, null, null)
    }

    private fun prepareExoplayer() {
        val uri = Uri.parse(BASE_URL)
        val mediaSource = buildMediaSource(uri)
        simpleExoplayer.prepare(mediaSource)
    }
}