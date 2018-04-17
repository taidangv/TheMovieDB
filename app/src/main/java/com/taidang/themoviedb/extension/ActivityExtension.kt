package com.taidang.themoviedb.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.taidang.themoviedb.presentation.TMDBApp


val Fragment.tmdbApp: TMDBApp
    get() = activity?.application as TMDBApp

val AppCompatActivity.tmdbApp: TMDBApp
    get() = application as TMDBApp

fun AppCompatActivity.setupToolbar(@IdRes toolbarId: Int, action: Toolbar.() -> Unit): Toolbar {
    val toolbar: Toolbar = checkNotNull(findViewById(toolbarId))
    return toolbar.run {
        action()
        this
    }
}