package krisanthe.exercise.mytvshows.screens.showList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import krisanthe.exercise.mytvshows.R
import krisanthe.exercise.mytvshows.model.ShowDetails

class ShowsAdapter : RecyclerView.Adapter<ShowViewHolder>() {

    private val itemClicks = PublishSubject.create<Int>()
    private var showList = mutableListOf<ShowDetails>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ShowViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.i_show, viewGroup, false)
        return ShowViewHolder(view, itemClicks)
    }

    override fun onBindViewHolder(showViewHolder: ShowViewHolder, i: Int) {
        showViewHolder.bind(showList[i])
    }

    fun observableClicks(): Observable<Int> {
        return itemClicks
    }

    fun swapAdapter(shows: MutableList<ShowDetails>) {
        this.showList.clear()
        this.showList.addAll(shows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (showList.size > 0) {
            showList.size
        } else {
            0
        }
    }
}