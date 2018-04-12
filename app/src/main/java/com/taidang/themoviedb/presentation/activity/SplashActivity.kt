package com.taidang.themoviedb.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.taidang.themoviedb.R
import com.taidang.themoviedb.extension.gone
import com.taidang.themoviedb.extension.visible
import com.taidang.themoviedb.presentation.TMDBApp
import com.taidang.themoviedb.presentation.contract.SplashContract
import com.taidang.themoviedb.presentation.di.module.SplashModule
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        (application as TMDBApp).appComponent
                .splashModule(SplashModule())
                .inject(this)

        presenter.attachView(this)
        presenter.fetchConfig()
    }

    override fun displayLoading() {
        vLoading.visible()
    }

    override fun hideLoading() {
        vLoading.gone()
    }

    override fun gotoMainScreen() {
        longToast("gotoMainScreen()")
    }

    override fun displayError(throwable: Throwable) {
        longToast("oops $throwable.localizedMessage")
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

}