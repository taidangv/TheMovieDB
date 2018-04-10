package com.taidang.themoviedb.repository

import com.taidang.themoviedb.domain.ConfigurationRepository
import com.taidang.themoviedb.domain.model.ApiConfiguration
import com.taidang.themoviedb.domain.model.Country
import com.taidang.themoviedb.repository.http.ConfigurationHttpClient
import com.taidang.themoviedb.repository.mapper.ApiConfigurationMapper
import com.taidang.themoviedb.repository.mapper.CountryMapper
import io.reactivex.Observable

class ConfigurationRemoteDataStore(
        private val httpClient: ConfigurationHttpClient,
        private val apiConfigurationMapper: ApiConfigurationMapper,
        private val countryMapper: CountryMapper)
    : ConfigurationRepository {

    override fun getApiConfiguration(): Observable<ApiConfiguration> {
        return httpClient.getApiConfiguration()
                .map { apiConfigurationMapper.transform(it) }
    }

    override fun getCountries(): Observable<List<Country>> {
        return httpClient.getCountries()
                .map { countryMapper.transform(it) }
    }

}