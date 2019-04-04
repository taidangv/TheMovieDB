package com.taidang.themoviedb.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.gone
import com.taidang.themoviedb.extension.visible
import com.taidang.themoviedb.presentation.activity.MovieDetailsActivity
import com.taidang.themoviedb.presentation.adapter.GridItemDecoration
import com.taidang.themoviedb.presentation.adapter.MoviesListingAdapter
import com.taidang.themoviedb.presentation.contract.base.IBaseView
import kotlinx.android.synthetic.main.fragment_movies_listing.vErrorMessage
import kotlinx.android.synthetic.main.fragment_movies_listing.vListingView
import kotlinx.android.synthetic.main.fragment_movies_listing.vLoading
import org.jetbrains.anko.support.v4.dip
import org.jetbrains.anko.support.v4.startActivity

abstract class MoviesListingBaseFragment<PRESENTER> : Fragment(), IBaseView<PRESENTER> {

    companion object {
        private const val SPAN_COUNT = 2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup recycler view
        vListingView.layoutManager = GridLayoutManager(context, SPAN_COUNT, RecyclerView.VERTICAL, false)
        val itemDecoration = GridItemDecoration(SPAN_COUNT, dip(3), false, 0)
        vListingView.addItemDecoration(itemDecoration)
    }


    protected fun displayMovies(movies: List<Movie>, imagesConfig: ImagesConfig, onItemClick: (Movie) -> Unit) {
        vListingView.adapter = MoviesListingAdapter(movies, imagesConfig, onItemClick)
        hideAllViewExcept(vListingView)
    }

    protected fun gotoMovieDetails(movie: Movie) {
        startActivity<MovieDetailsActivity>(MovieDetailsActivity.EXTRA_MOVIE_ID to movie.id)
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

    private fun hideAllViewExcept(tobeDisplay: View? = null) {
        vLoading.gone()
        vErrorMessage.gone()
        vListingView.gone()
        tobeDisplay?.visible()
    }
}
