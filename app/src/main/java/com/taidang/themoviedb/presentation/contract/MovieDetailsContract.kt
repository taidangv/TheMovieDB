package com.taidang.themoviedb.presentation.contract

import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.presentation.contract.base.IBasePresenter
import com.taidang.themoviedb.presentation.contract.base.IBaseView

interface MovieDetailsContract {

    interface View : IBaseView<Presenter> {
        fun showDetails(movie: Movie)
    }

    interface Presenter : IBasePresenter<View>
}