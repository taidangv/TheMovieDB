package com.taidang.themoviedb.presentation.activity

import android.os.Bundle
import android.util.Log
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.gone
import com.taidang.themoviedb.extension.tmdbApp
import com.taidang.themoviedb.extension.visible
import com.taidang.themoviedb.presentation.contract.MovieDetailsContract
import com.taidang.themoviedb.presentation.di.module.MovieDetailsModule
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity(), MovieDetailsContract.View {

    companion object {
        const val EXTRA_MOVIE_ID = "extra-movie-id"
    }

    @Inject
    override lateinit var mPresenter: MovieDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val id = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        tmdbApp.appComponent
                .plus(MovieDetailsModule(id))
                .inject(this)

        with(mPresenter) {
            attachView(this@MovieDetailsActivity)
            start()
        }
    }

    override fun showDetails(movie: Movie) {
        Log.d("app debug", movie.toString())
    }

    override fun displayLoading() {
        vLoading.visible()
    }

    override fun hideLoading() {
        vLoading.gone()
    }

    override fun displayError(throwable: Throwable) {
        showErrorMessage(throwable.message)
    }

    override fun onDestroy() {
        mPresenter.destroy()
        super.onDestroy()
    }
}