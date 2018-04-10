package com.taidang.themoviedb.domain

import com.taidang.themoviedb.domain.model.ApiConfiguration
import com.taidang.themoviedb.domain.model.Country
import io.reactivex.Observable

interface ConfigurationRepository {
    fun getApiConfiguration(): Observable<ApiConfiguration>
    fun getCountries(): Observable<List<Country>>
}