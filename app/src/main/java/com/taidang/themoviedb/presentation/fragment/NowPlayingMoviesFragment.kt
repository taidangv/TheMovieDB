package com.taidang.themoviedb.presentation.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.gone
import com.taidang.themoviedb.extension.visible
import com.taidang.themoviedb.presentation.adapter.NowPlayingMoviesAdapter
import com.taidang.themoviedb.presentation.contract.NowPlayingMoviesContract
import kotlinx.android.synthetic.main.fragment_now_playing.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class NowPlayingMoviesFragment : Fragment(), NowPlayingMoviesContract.View {

    @Inject
    override lateinit var mPresenter: NowPlayingMoviesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()

        // TODO do inject here
    }

    private fun setupViews() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vMoviesListing.layoutManager = layoutManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_now_playing, container)
    }

    override fun displayMovies(movies: List<Movie>) {
        val adapter = NowPlayingMoviesAdapter(movies) { clickedItem ->
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