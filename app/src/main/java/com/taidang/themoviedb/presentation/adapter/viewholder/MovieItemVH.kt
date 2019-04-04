package com.taidang.themoviedb.presentation.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.ImageSize
import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.domain.model.Movie

class MovieItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Can get rid of these boilerplate code by using LayoutContainer with experimental mode enabled
    // https://kotlinlang.org/docs/tutorials/android-plugin.html#layoutcontainer-support
    private val ivPoster = itemView.findViewById(R.id.vMovieBackdrop) as ImageView
    private val tvVote = itemView.findViewById(R.id.vMovieVote) as TextView

    fun bind(movie: Movie, imagesConfig: ImagesConfig) {
        tvVote.text = movie.vote.toString()
        Glide.with(itemView.context)
                .load(imagesConfig.buildPosterUrl(movie.posterPath, ImageSize.MEDIUM))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPoster)
    }
}
