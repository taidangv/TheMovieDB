package com.taidang.themoviedb.presentation.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.taidang.themoviedb.presentation.adapter.CastAdapter
import com.taidang.themoviedb.presentation.contract.MovieDetailsContract
import com.taidang.themoviedb.presentation.di.module.MovieDetailsModule
import kotlinx.android.synthetic.main.activity_movie_details.vBackdrop
import kotlinx.android.synthetic.main.activity_movie_details.vContentRating
import kotlinx.android.synthetic.main.activity_movie_details.vLoading
import kotlinx.android.synthetic.main.activity_movie_details.vMovieDetailsListing
import kotlinx.android.synthetic.main.include_movie_details_cast_section.vCastListing
import kotlinx.android.synthetic.main.include_movie_details_description_section.vDescription
import kotlinx.android.synthetic.main.include_movie_details_description_section.vPoster
import kotlinx.android.synthetic.main.include_movie_details_overview_section.vName
import kotlinx.android.synthetic.main.include_movie_details_overview_section.vPrimaryInfo
import kotlinx.android.synthetic.main.include_movie_details_overview_section.vTagline
import kotlinx.android.synthetic.main.include_movie_details_product_info.vInfoCompanies
import kotlinx.android.synthetic.main.include_movie_details_product_info.vInfoCountry
import kotlinx.android.synthetic.main.include_movie_details_product_info.vInfoKeyword
import kotlinx.android.synthetic.main.include_movie_details_product_info.vInfoReleaseDate
import java.text.SimpleDateFormat
import java.util.Locale
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
                .load(imagesConfig.buildBackdropUrl(movie.backdropPath, ImageSize.MEDIUM))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(vBackdrop)
        Glide.with(this)
                .load(imagesConfig.buildPosterUrl(movie.posterPath, ImageSize.SMALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(vPoster)

        vName.text = movie.title
        movie.details?.run {
            vTagline.text = tagline
            val releaseDate = SimpleDateFormat("yyyy", Locale.US).format(movie.releaseDate)
            vPrimaryInfo.text = "${getDurationStr()}  |  $releaseDate  |  ${genres.joinToString()}"
            vDescription.text = description
        }
        movie.details?.contentRating?.let {
            vContentRating.visible()
            vContentRating.text = it
        } ?: vContentRating.gone()
    }

    private fun renderCasts(casts: List<Cast>) {
        val filteredList = casts.filter {
            it.profile_path != null && it.profile_path.isNotEmpty()
        }
        vCastListing.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        vCastListing.setHasFixedSize(true)
        vCastListing.adapter = CastAdapter(filteredList, imagesConfig)

        ContextCompat.getDrawable(this, R.drawable.cast_listing_divider)?.let {
            val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
            dividerItemDecoration.setDrawable(it)
            vCastListing.addItemDecoration(dividerItemDecoration)
        }
    }

    private fun renderProductInfo(movie: Movie) {
        vInfoReleaseDate.text = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(movie.releaseDate)
        movie.details?.run {
            vInfoCountry.text = countries.joinToString()
            vInfoCompanies.text = companies.map { it.name }.joinToString()
            vInfoKeyword.text = keywords.joinToString()
        }
    }
}
