package krisanthe.exercise.mytvshows.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import krisanthe.exercise.mytvshows.R

fun Fragment.launchFragment(context: Context) {
    val supportFragmentManager = (context as AppCompatActivity).supportFragmentManager
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.container, this, this.javaClass.name)
        .addToBackStack(this.javaClass.name)
        .commitAllowingStateLoss()
}