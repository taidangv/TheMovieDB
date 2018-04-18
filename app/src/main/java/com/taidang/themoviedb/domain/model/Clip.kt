package com.taidang.themoviedb.domain.model

data class Clip(
        val id: String,
        val key: String,
        val name: String,
        val site: String,
        val type: String,
        val size: Int
)