package com.taidang.themoviedb.presentation.adapter

import android.view.View
import androidx.viewpager.widget.ViewPager


class ZoomOutPageTransformer : ViewPager.PageTransformer {

    companion object {
        private const val MIN_SCALE = 0.9F
    }

    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        val pageHeight = view.height

        // Modify the default slide transition to shrink the page as well
        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
        val vertMargin = pageHeight * (1 - scaleFactor) / 2
        val horzMargin = pageWidth * (1 - scaleFactor) / 2
        if (position < 0) {
            view.translationX = horzMargin - vertMargin / 2
        } else {
            view.translationX = -horzMargin + vertMargin / 2
        }

        // Scale the page down (between MIN_SCALE and 1)
        view.scaleX = scaleFactor
        view.scaleY = scaleFactor
    }
}
