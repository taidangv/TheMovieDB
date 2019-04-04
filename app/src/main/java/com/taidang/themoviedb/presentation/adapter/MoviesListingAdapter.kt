package com.taidang.themoviedb.presentation.adapter

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.inflate
import com.taidang.themoviedb.presentation.adapter.viewholder.MovieItemVH

class MoviesListingAdapter(
    private val movies: List<Movie>,
    private val imagesConfig: ImagesConfig,
    private val itemClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieItemVH>() {

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
