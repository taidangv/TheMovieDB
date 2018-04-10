package com.taidang.themoviedb.repository.http

import com.taidang.themoviedb.repository.response.ApiConfigurationEntity
import com.taidang.themoviedb.repository.response.CountryEntity
import io.reactivex.Observable


interface ConfigurationHttpClient {

    fun getApiConfiguration(): Observable<ApiConfigurationEntity>

    fun getCountries(): Observable<List<CountryEntity>>

}