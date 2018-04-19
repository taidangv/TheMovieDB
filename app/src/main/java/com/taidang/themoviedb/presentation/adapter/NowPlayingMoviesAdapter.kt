package com.taidang.themoviedb.presentation.adapter

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.ImageSize
import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.inflate

class NowPlayingMoviesAdapter(private val movies: List<Movie>,
                              private val imagesConfig: ImagesConfig,
                              private val itemClickListener: (Movie) -> Unit)
    : RecyclerView.Adapter<MovieItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemVH {
        val vh = MovieItemVH(parent.inflate(R.layout.item_now_playing_movie_port))
        // item click listener
        vh.itemView.setOnClickListener { itemClickListener(movies[vh.adapterPosition]) }
        // vote icon color filter
        vh.itemView.findViewById<ImageView>(R.id.vStar).apply {
            drawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
        }
        return vh
    }

    override fun onBindViewHolder(holder: MovieItemVH, position: Int) {
        holder.bind(movies[holder.adapterPosition], imagesConfig)
    }

    override fun getItemCount() = movies.size
}


class MovieItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Can get rid of these boilerplate code by using LayoutContainer with experimental mode enabled
    // https://kotlinlang.org/docs/tutorials/android-plugin.html#layoutcontainer-support
    private val vImage = itemView.findViewById(R.id.vMovieBackdrop) as ImageView
    private val vVote = itemView.findViewById(R.id.vMovieVote) as TextView

    fun bind(movie: Movie, imagesConfig: ImagesConfig) {
        vVote.text = movie.vote.toString()
        Glide.with(itemView.context)
                .load(imagesConfig.buildPosterUrl(movie.posterPath, ImageSize.MEDIUM))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(vImage)
    }
}