package com.taidang.themoviedb.di.module

import com.taidang.themoviedb.domain.ConfigurationRepository
import com.taidang.themoviedb.domain.MovieRepository
import com.taidang.themoviedb.presentation.di.ApplicationScoped
import com.taidang.themoviedb.repository.ConfigurationRemoteDataStore
import com.taidang.themoviedb.repository.MovieRemoteDataStore
import com.taidang.themoviedb.repository.http.ConfigurationHttpClient
import com.taidang.themoviedb.repository.http.MovieHttpClient
import com.taidang.themoviedb.repository.mapper.ApiConfigurationMapper
import com.taidang.themoviedb.repository.mapper.CountryMapper
import com.taidang.themoviedb.repository.mapper.MovieMapper
import com.taidang.themoviedb.repository.mapper.MoviesInfoMapper
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScoped
    fun providesConfigurationRepo(configurationHttpClient: ConfigurationHttpClient,
                                  apiConfigurationMapper: ApiConfigurationMapper,
                                  countryMapper: CountryMapper): ConfigurationRepository {
        return ConfigurationRemoteDataStore(configurationHttpClient, apiConfigurationMapper, countryMapper)
    }

    @Provides
    @ApplicationScoped
    fun providesMovieRepo(movieHttpClient: MovieHttpClient, moviesInfoMapper: MoviesInfoMapper, movieMapper: MovieMapper): MovieRepository {
        return MovieRemoteDataStore(movieHttpClient, moviesInfoMapper, movieMapper)
    }
}