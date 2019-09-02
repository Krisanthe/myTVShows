package krisanthe.exercise.mytvshows.screens.showList.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import io.reactivex.subjects.PublishSubject
import krisanthe.exercise.mytvshows.R
import krisanthe.exercise.mytvshows.model.ShowDetails

class ShowViewHolder(private val view: View, clickedSub: PublishSubject<Int>) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.iv_show_image)
    lateinit var showImage: ImageView

    @BindView(R.id.tv_title)
    lateinit var showTitle: TextView

    @BindView(R.id.tv_genres)
    lateinit var showGenres: TextView

    init {
        ButterKnife.bind(this, view)
        view.setOnClickListener { clickedSub.onNext(adapterPosition) }
    }

    fun bind(showDetails: ShowDetails) {
        showDetails.show?.let { show ->
            if (show.image != null && !TextUtils.isEmpty(show.image!!.medium)) {
                Glide.with(view.context).load(show.image!!.medium).into(showImage)
            }

            showTitle.text = if (TextUtils.isEmpty(show.name)) "title missing" else show.name
            showGenres.text = if (show.genres.isEmpty()) "genres missing" else TextUtils.join(", ", show.genres)
        }

    }
}