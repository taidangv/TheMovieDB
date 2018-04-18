package com.taidang.themoviedb.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.taidang.themoviedb.R
import com.taidang.themoviedb.presentation.adapter.MainContentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onPageChangeListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainContentPagerAdapter(this, supportFragmentManager)
        vMainPager.adapter = adapter
        vMainPager.onPageChangeListener {
            onPageSelected { vToolbar.title = adapter.getPageTitle(it) }
        }

        vToolbar.title = adapter.getPageTitle(vMainPager.currentItem)
    }
}
