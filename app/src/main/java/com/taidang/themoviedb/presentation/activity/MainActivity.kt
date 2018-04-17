package com.taidang.themoviedb.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.taidang.themoviedb.R
import com.taidang.themoviedb.extension.setupToolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.vToolbar) {
            title = "Homepage"
        }
    }
}
