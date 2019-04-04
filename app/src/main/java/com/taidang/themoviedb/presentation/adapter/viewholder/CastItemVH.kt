package com.taidang.themoviedb.presentation.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.Cast
import com.taidang.themoviedb.domain.model.ImageSize
import com.taidang.themoviedb.domain.model.ImagesConfig

class CastItemVH(itemView: View, private val imagesConfig: ImagesConfig) : RecyclerView.ViewHolder(itemView) {

    // Can get rid of these boilerplate code by using LayoutContainer with experimental mode enabled
    // https://kotlinlang.org/docs/tutorials/android-plugin.html#layoutcontainer-support
    private val ivPhoto = itemView.findViewById(R.id.vCastPic) as ImageView
    private val tvName = itemView.findViewById(R.id.vCastName) as TextView
    private val tvCharacter = itemView.findViewById(R.id.vCastCharacter) as TextView

    fun bind(cast: Cast) {
        with(cast) {
            Glide.with(itemView.context)
                    .load(imagesConfig.buildProfileUrl(profile_path, ImageSize.MEDIUM))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivPhoto)
            tvName.text = name
            tvCharacter.text = character
        }
    }

}
