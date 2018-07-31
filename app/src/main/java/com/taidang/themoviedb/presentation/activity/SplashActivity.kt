package com.taidang.themoviedb.presentation.activity

import android.os.Bundle
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.Country
import com.taidang.themoviedb.extension.gone
import com.taidang.themoviedb.extension.tmdbApp
import com.taidang.themoviedb.extension.visible
import com.taidang.themoviedb.presentation.contract.SplashContract
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    override lateinit var mPresenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tmdbApp.appComponent.inject(this)

        with(mPresenter) {
            attachView(this@SplashActivity)
            start()
        }
    }

    override fun displayChooseCountryDialog(countries: List<Country>) {
        val title = getString(R.string.dialog_title_choose_country)
        val displayLst = countries.map { "${it.name} (${it.isoCode})" }
        selector(title, displayLst
        ) { _, idx ->
            mPresenter.pickCountry(countries[idx])
        }
    }

    override fun displayLoading() {
        vLoading.visible()
    }

    override fun hideLoading() {
        vLoading.gone()
    }

    override fun gotoMainScreen() {
        startActivity<MainActivity>()
        finish()
    }

    override fun displayError(throwable: Throwable) {
        showErrorMessage(throwable.message)
    }

    override fun onDestroy() {
        mPresenter.destroy()
        super.onDestroy()
    }

}