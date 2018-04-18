package com.taidang.themoviedb.presentation.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.gone
import com.taidang.themoviedb.extension.tmdbApp
import com.taidang.themoviedb.extension.visible
import com.taidang.themoviedb.presentation.adapter.GridItemDecoration
import com.taidang.themoviedb.presentation.adapter.NowPlayingMoviesAdapter
import com.taidang.themoviedb.presentation.contract.NowPlayingMoviesContract
import com.taidang.themoviedb.presentation.di.module.NowPlayingMoviesModule
import com.taidang.themoviedb.presentation.manager.AppConfigManager
import kotlinx.android.synthetic.main.fragment_now_playing.*
import org.jetbrains.anko.support.v4.dip
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class NowPlayingMoviesFragment : Fragment(), NowPlayingMoviesContract.View {

    @Inject
    lateinit var mAppConfigManager: AppConfigManager
    @Inject
    override lateinit var mPresenter: NowPlayingMoviesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tmdbApp.appComponent
                .plus(NowPlayingMoviesModule())
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val spanCount = 2
        vMoviesListing.layoutManager = GridLayoutManager(context, spanCount, RecyclerView.VERTICAL, false)

        val itemDecoration = GridItemDecoration(spanCount, dip(3), false, 0)
        vMoviesListing.addItemDecoration(itemDecoration)

        with(mPresenter) {
            attachView(this@NowPlayingMoviesFragment)
            start()
        }
    }

    override fun displayMovies(movies: List<Movie>) {
        val adapter = NowPlayingMoviesAdapter(movies, mAppConfigManager.getImagesConfig()) { clickedItem ->
            mPresenter.chooseMovie(clickedItem)
        }
        vMoviesListing.adapter = adapter
        hideAllViewExcept(vMoviesListing)
    }

    override fun gotoDetailsMovie(movie: Movie) {
        toast("gotoDetailsMovie: ${movie.title}")
    }

    override fun displayLoading() {
        hideAllViewExcept(vLoading)
    }

    override fun hideLoading() {
        hideAllViewExcept()
    }

    override fun displayError(throwable: Throwable) {
        vErrorMessage.text = throwable.message
        hideAllViewExcept(vErrorMessage)
    }

    override fun onDestroy() {
        mPresenter.destroy()
        super.onDestroy()
    }

    private fun hideAllViewExcept(tobeDisplay: View? = null) {
        vLoading.gone()
        vErrorMessage.gone()
        vMoviesListing.gone()
        tobeDisplay?.visible()
    }
}