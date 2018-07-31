package com.taidang.themoviedb.presentation.fragment

import android.os.Bundle
import android.view.View
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.tmdbApp
import com.taidang.themoviedb.presentation.contract.UpcomingMoviesContract
import com.taidang.themoviedb.presentation.manager.AppConfigManager
import javax.inject.Inject

class UpcomingMoviesFragment : MoviesListingBaseFragment<UpcomingMoviesContract.Presenter>(),
        UpcomingMoviesContract.View {

    @Inject
    lateinit var mAppConfigManager: AppConfigManager
    @Inject
    override lateinit var mPresenter: UpcomingMoviesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tmdbApp.appComponent
                .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mPresenter) {
            attachView(this@UpcomingMoviesFragment)
            start()
        }
    }

    override fun displayMovies(movies: List<Movie>) {
        super.displayMovies(movies, mAppConfigManager.getImagesConfig()) { clickedItem ->
            mPresenter.chooseMovie(clickedItem)
        }
    }

    override fun gotoDetailsMovie(movie: Movie) {
        super.gotoMovieDetails(movie)
    }

    override fun onDestroy() {
        mPresenter.destroy()
        super.onDestroy()
    }
}