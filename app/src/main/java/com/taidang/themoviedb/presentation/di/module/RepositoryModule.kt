package com.taidang.themoviedb.di.module

import com.taidang.themoviedb.domain.ConfigurationRepository
import com.taidang.themoviedb.presentation.di.ApplicationScoped
import com.taidang.themoviedb.presentation.di.module.MapperModule
import com.taidang.themoviedb.repository.ConfigurationRemoteDataStore
import com.taidang.themoviedb.repository.http.ConfigurationHttpClient
import com.taidang.themoviedb.repository.mapper.ApiConfigurationMapper
import com.taidang.themoviedb.repository.mapper.CountryMapper
import dagger.Module
import dagger.Provides

@Module(includes = [(MapperModule::class)])
class RepositoryModule {

    @Provides
    @ApplicationScoped
    fun providesConfigurationRepo(configurationHttpClient: ConfigurationHttpClient,
                                  apiConfigurationMapper: ApiConfigurationMapper,
                                  countryMapper: CountryMapper): ConfigurationRepository {
        return ConfigurationRemoteDataStore(configurationHttpClient, apiConfigurationMapper, countryMapper)
    }
}