package com.taidang.themoviedb.repository.http

import com.taidang.themoviedb.repository.response.ApiConfigurationEntity
import com.taidang.themoviedb.repository.response.CountryEntity
import io.reactivex.Observable
import retrofit2.http.GET


interface ConfigurationHttpClient {

    @GET("configuration")
    fun getApiConfiguration(): Observable<ApiConfigurationEntity>

    @GET("configuration/countries")
    fun getCountries(): Observable<List<CountryEntity>>

}