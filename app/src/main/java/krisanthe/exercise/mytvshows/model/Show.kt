package krisanthe.exercise.mytvshows.model

import com.google.gson.annotations.Expose
import java.io.Serializable


class Show : Serializable {

    @Expose
    var id: Int = 0

    @Expose
    var name: String = ""

    @Expose
    var genres = mutableListOf<String>()


    @Expose
    var image: Image? = null
}