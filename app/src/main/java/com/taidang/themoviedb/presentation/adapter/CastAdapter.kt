package com.taidang.themoviedb.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.taidang.themoviedb.R
import com.taidang.themoviedb.domain.model.Cast
import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.extension.inflate
import com.taidang.themoviedb.presentation.adapter.viewholder.CastItemVH

class CastAdapter(private val casts: List<Cast>, private val imagesConfig: ImagesConfig) : RecyclerView.Adapter<CastItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CastItemVH(parent.inflate(R.layout.item_cast), imagesConfig)

    override fun onBindViewHolder(holder: CastItemVH, position: Int) = holder.run {
        bind(casts[adapterPosition])
    }

    override fun getItemCount() = casts.size
}