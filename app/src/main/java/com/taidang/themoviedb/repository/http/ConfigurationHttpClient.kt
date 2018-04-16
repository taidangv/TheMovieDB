package com.taidang.themoviedb.repository.http

import com.taidang.themoviedb.repository.response.ApiConfigurationEntity
import com.taidang.themoviedb.repository.response.CountryEntity
import io.reactivex.Single
import retrofit2.http.GET


interface ConfigurationHttpClient {

    @GET("configuration")
    fun getApiConfiguration(): Single<ApiConfigurationEntity>

    @GET("configuration/countries")
    fun getCountries(): Single<List<CountryEntity>>

}