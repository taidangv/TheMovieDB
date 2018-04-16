package com.taidang.themoviedb.domain

import com.taidang.themoviedb.domain.model.ApiConfiguration
import com.taidang.themoviedb.domain.model.Country
import io.reactivex.Single

interface ConfigurationRepository {
    fun getApiConfiguration(): Single<ApiConfiguration>
    fun getCountries(): Single<List<Country>>
}