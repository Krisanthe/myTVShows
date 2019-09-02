package krisanthe.exercise.mytvshows.model

import com.google.gson.annotations.Expose
import java.io.Serializable

class ShowDetails : Serializable {

    @Expose
    var show: Show? = null
}