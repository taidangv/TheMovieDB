package com.taidang.themoviedb.presentation.manager

import com.taidang.themoviedb.domain.model.Country
import com.taidang.themoviedb.domain.model.ImagesConfig

interface AppConfigManager {

    fun getImagesConfig(): ImagesConfig?
    fun saveImagesConfig(imagesConfig: ImagesConfig?)
    fun getCountries(): List<Country>
    fun saveCountries(countries: List<Country>)
    fun saveCurrentCountry(country: Country?)
    fun getCurrentCountry(): Country?

}