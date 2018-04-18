package com.taidang.themoviedb.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.extension.inflate

class NowPlayingMoviesAdapter(private val movies: List<Movie>, private val itemClickListener: (Movie) -> Unit) : RecyclerView.Adapter<MovieItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemVH {
        return MovieItemVH(parent.inflate(R.layout.item_now_playing_movie))
                .apply {
                    itemView.setOnClickListener { itemClickListener(movies[adapterPosition]) }
                }
    }

    override fun onBindViewHolder(holder: MovieItemVH, position: Int) = holder.bind(movies[holder.adapterPosition])

    override fun getItemCount() = movies.size
}


class MovieItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Can get rid of these boilerplate code by using LayoutContainer with experimental mode enabled
    // https://kotlinlang.org/docs/tutorials/android-plugin.html#layoutcontainer-support
    private val vImage: ImageView = itemView.findViewById(R.id.vMovieBackdrop)
    private val vName = itemView.findViewById(R.id.vMovieName) as TextView
    private val vGenres = itemView.findViewById(R.id.vMovieGenres) as TextView
    private val vDuration = itemView.findViewById(R.id.vMovieDuration) as TextView
    private val vVote = itemView.findViewById(R.id.vMovieVote) as TextView

    fun bind(movie: Movie) = with(movie) {
        vName.text = title
        vVote.text = vote.toString()

        // TODO bind more info
    }
}