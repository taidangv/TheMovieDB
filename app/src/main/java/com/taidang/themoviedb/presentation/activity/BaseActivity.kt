package com.taidang.themoviedb.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.longToast

abstract class BaseActivity : AppCompatActivity() {

    fun showErrorMessage(error: String?) {
        longToast("Oops! $error")
    }
}
