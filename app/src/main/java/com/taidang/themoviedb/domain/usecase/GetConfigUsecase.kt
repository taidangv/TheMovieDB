package com.taidang.themoviedb.domain.usecase

import com.taidang.themoviedb.domain.ConfigurationRepository
import com.taidang.themoviedb.domain.SchedulerFactory
import com.taidang.themoviedb.domain.model.ApiConfiguration
import com.taidang.themoviedb.domain.model.Country
import io.reactivex.Observable

class GetConfigUsecase(schedulerFactory: SchedulerFactory, private val configRepository: ConfigurationRepository)
    : BaseUsecase(schedulerFactory) {

    fun getApiConfiguration(): Observable<ApiConfiguration> = configRepository.getApiConfiguration()
            .subscribeOn(schedulerFactory.workerScheduler())
            .observeOn(schedulerFactory.callbackScheduler())

    fun getCountries(): Observable<List<Country>> = configRepository.getCountries()
            .subscribeOn(schedulerFactory.workerScheduler())
            .observeOn(schedulerFactory.callbackScheduler())

}