package com.taidang.themoviedb.domain.model

data class MovieDetails(val duration: Int,
                        val genres: List<String>,
                        val description: String,
                        val casts: List<Cast>,
                        val clips: List<Clip>,
                        val companies: List<Company>,
                        val status: String,
                        val homepage: String,
                        val tagline: String,
                        val keywords: List<String>)