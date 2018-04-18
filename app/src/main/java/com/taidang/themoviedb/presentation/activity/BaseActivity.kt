package com.taidang.themoviedb.presentation.activity

import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.longToast

open abstract class BaseActivity : AppCompatActivity() {

    fun showErrorMessage(error: String?) {
        longToast("Oops! $error")
    }
}