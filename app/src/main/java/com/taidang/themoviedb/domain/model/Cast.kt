package com.taidang.themoviedb.domain.model

data class Cast(
        val id: Int,
        val name: String,
        val character: String,
        val profile_path: String?,
        val castId: Int,
        val creditId: String,
        val order: Int
)