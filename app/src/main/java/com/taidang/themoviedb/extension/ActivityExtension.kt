package com.taidang.themoviedb.extension

import android.app.Activity
import android.support.v4.app.Fragment
import com.taidang.themoviedb.presentation.TMDBApp


val Activity.tmdbApp: TMDBApp
    get() = application as TMDBApp

val Fragment.tmdbApp: TMDBApp
    get() = activity?.application as TMDBApp