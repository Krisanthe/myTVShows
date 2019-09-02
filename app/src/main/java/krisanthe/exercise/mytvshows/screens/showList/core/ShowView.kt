package krisanthe.exercise.mytvshows.screens.showList.core

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import krisanthe.exercise.mytvshows.R
import krisanthe.exercise.mytvshows.model.ShowDetails
import krisanthe.exercise.mytvshows.screens.showList.ShowsFragment
import krisanthe.exercise.mytvshows.screens.showList.adapter.ShowsAdapter

class ShowView(val context: ShowsFragment) {

    @BindView(R.id.rv_show_list)
    lateinit var list: RecyclerView

    @BindView(R.id.search)
    lateinit var search: SearchView

    private val newSearchValue = PublishSubject.create<String>()
    private var view: View
    private var adapter: ShowsAdapter

    init {
        val parent = FrameLayout(context.activity as Context)
        parent.layoutParams =
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        view = LayoutInflater.from(context.activity).inflate(R.layout.a_show_list, parent, true)
        ButterKnife.bind(this, view)

        list.addItemDecoration(DividerItemDecoration(context.activity, LinearLayoutManager.VERTICAL))
        list.setHasFixedSize(true)

        setupSearchViewListener()
        search.isIconified = false

        adapter = ShowsAdapter()

        list.adapter = adapter
        val layoutManager = LinearLayoutManager(context.activity)
        list.layoutManager = layoutManager

    }

    fun getClickObservable(): Observable<Int> {
        return adapter.observableClicks()
    }

    fun getNewSearchValueObservable(): Observable<String> {
        return newSearchValue
    }

    fun view(): View {
        return view
    }

    private fun setupSearchViewListener() {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchValue: String): Boolean {
                callSearch(searchValue)
                return true
            }

            override fun onQueryTextChange(searchValue: String): Boolean {
                callSearch(searchValue)
                return true
            }

        })
    }

    fun callSearch(searchValue: String) {
        if (!TextUtils.isEmpty(searchValue) && searchValue.length >= 3) {
            newSearchValue.onNext(searchValue)
        } else newSearchValue.onNext("")

    }

    fun showNoConnectionDialog() {
        AlertDialog.Builder(context.activity)
            .setTitle("Error")
            .setMessage("No internet connection")
            .setPositiveButton("Ok") { d, _ -> d.dismiss() }
            .create()
            .show()
    }

    fun refreshAdapter(shows: MutableList<ShowDetails>) {
        adapter.swapAdapter(shows)
    }
}