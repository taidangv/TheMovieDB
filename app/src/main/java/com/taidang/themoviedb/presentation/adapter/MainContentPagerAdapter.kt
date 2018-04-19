package com.taidang.themoviedb.presentation.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.taidang.themoviedb.R
import com.taidang.themoviedb.presentation.fragment.NowPlayingMoviesFragment
import com.taidang.themoviedb.presentation.fragment.UpcomingMoviesFragment

class MainContentPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        private const val TOTAL_PAGES = 2
        private const val INDEX_NOW_PLAYING = 0
        private const val INDEX_UPCOMING = 1
    }

    override fun getItem(position: Int): Fragment = when (position) {
        INDEX_NOW_PLAYING -> NowPlayingMoviesFragment()
        INDEX_UPCOMING -> UpcomingMoviesFragment()
        else -> throw IllegalArgumentException()
    }

    override fun getPageTitle(position: Int): String = when (position) {
        INDEX_NOW_PLAYING -> context.getString(R.string.tab_name_now_playing_movie)
        INDEX_UPCOMING -> context.getString(R.string.tab_name_upcoming_movie)
        else -> throw IllegalArgumentException()
    }

    override fun getCount() = TOTAL_PAGES
}