package com.taidang.themoviedb.repository.response

class ImagesConfigEntity(val base_url: String,
                         val secure_base_url: String,
                         val backdrop_sizes: List<String>,
                         val logo_sizes: List<String>,
                         val poster_sizes: List<String>,
                         val profile_sizes: List<String>,
                         val still_sizes: List<String>)

class ApiConfigurationEntity(val images: ImagesConfigEntity)

class CountryEntity(val iso_3166_1: String, val english_name: String)