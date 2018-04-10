package com.taidang.themoviedb.domain.model

data class ImagesConfig(val baseUrl: String,
                        val backdropSizes: List<String>,
                        val logoSizes: List<String>,
                        val posterSizes: List<String>,
                        val profileSizes: List<String>)