package com.taidang.themoviedb.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.taidang.themoviedb.presentation.di.ApplicationScoped
import com.taidang.themoviedb.presentation.di.DependencyName
import com.taidang.themoviedb.repository.http.ConfigurationHttpClient
import com.taidang.themoviedb.repository.http.MovieHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    @ApplicationScoped
    fun providesGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd")
                .create()
    }

    @Provides
    @ApplicationScoped
    fun providesOkHttpClient(@Named(DependencyName.DI_NAME_DEBUGGABLE) isDebug: Boolean, @Named(DependencyName.DI_NAME_API_KEY) apiKey: String): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            println("OkHttpDebug $it")
        })
        loggingInterceptor.level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val networkInterceptor = Interceptor { chain ->
            val request = chain.request()
            val httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()
            chain.proceed(request.newBuilder().url(httpUrl).build())
        }

        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(networkInterceptor)
                .build()
    }

    @Provides
    @ApplicationScoped
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson, @Named(DependencyName.DI_NAME_API_BASE_URL) baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @ApplicationScoped
    fun providesConfigurationHttpClient(retrofit: Retrofit): ConfigurationHttpClient {
        return retrofit.create(ConfigurationHttpClient::class.java)
    }

    @Provides
    @ApplicationScoped
    fun providesMovieHttpClient(retrofit: Retrofit): MovieHttpClient {
        return retrofit.create(MovieHttpClient::class.java)
    }

}
