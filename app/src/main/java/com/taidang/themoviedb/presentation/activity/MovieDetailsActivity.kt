package com.taidang.themoviedb.presentation.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.Cast
import com.taidang.themoviedb.domain.model.ImageSize
import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.gone
import com.taidang.themoviedb.extension.tmdbApp
import com.taidang.themoviedb.extension.visible
import com.taidang.themoviedb.presentation.contract.MovieDetailsContract
import com.taidang.themoviedb.presentation.di.module.MovieDetailsModule
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.include_movie_details_description_section.*
import kotlinx.android.synthetic.main.include_movie_details_overview_section.*
import kotlinx.android.synthetic.main.include_movie_details_product_info.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity(), MovieDetailsContract.View {

    companion object {
        const val EXTRA_MOVIE_ID = "extra-movie-id"
    }

    @Inject
    override lateinit var mPresenter: MovieDetailsContract.Presenter
    @Inject
    lateinit var imagesConfig: ImagesConfig

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
        renderOverview(movie)
        renderProductInfo(movie)
        renderCasts(movie.details?.let { it.casts } ?: emptyList())
        vMovieDetailsListing.visible()
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


    private fun renderOverview(movie: Movie) {
        Glide.with(this)
                .load(imagesConfig.buildBackdropUrl(movie.backdropPath, ImageSize.LARGE))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(vBackdrop)
        Glide.with(this)
                .load(imagesConfig.buildPosterUrl(movie.posterPath, ImageSize.SMALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(vPoster)

        vName.text = movie.title
        movie.details?.run {
            vTagline.text = tagline
            val releaseDate = SimpleDateFormat("YYYY", Locale.US).format(movie.releaseDate)
            vPrimaryInfo.text = "${getDurationStr()}  |  $releaseDate  |  ${genres.joinToString()}"
            vDescription.text = description
        }
    }

    private fun renderCasts(casts: List<Cast>) {

    }

    private fun renderProductInfo(movie: Movie) {
        vInfoReleaseDate.text = SimpleDateFormat("MMM dd, YYYY", Locale.US).format(movie.releaseDate)
        movie.details?.run {
            vInfoCountry.text = countries.joinToString()
            vInfoCompanies.text = companies.map { it.name }.joinToString()
            vInfoKeyword.text = keywords.joinToString()
        }
    }
}