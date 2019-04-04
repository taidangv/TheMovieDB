package com.taidang.themoviedb.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
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
