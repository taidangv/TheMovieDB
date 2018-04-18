package com.taidang.themoviedb.domain.model

data class MovieDetails(val duration: Int,
                        val homepage: String,
                        val genres: List<String>,
                        val description: String,
                        val companies: List<Company>,
                        val status: String,
                        val tagline: String)