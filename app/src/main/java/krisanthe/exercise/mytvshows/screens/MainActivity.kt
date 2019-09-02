package krisanthe.exercise.mytvshows.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import krisanthe.exercise.mytvshows.R
import krisanthe.exercise.mytvshows.screens.showList.ShowsFragment
import krisanthe.exercise.mytvshows.utils.launchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
        ShowsFragment().launchFragment(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            System.exit(0)
        }
    }
}
