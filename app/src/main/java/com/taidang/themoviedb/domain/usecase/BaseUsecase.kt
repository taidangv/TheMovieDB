package com.taidang.themoviedb.domain.usecase

import com.taidang.themoviedb.domain.SchedulerFactory

abstract class BaseUsecase(protected val schedulerFactory: SchedulerFactory)