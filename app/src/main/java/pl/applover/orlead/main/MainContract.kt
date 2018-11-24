package pl.applover.orlead.main

import pl.applover.kotlinmvp.BaseMvpPresenter
import pl.applover.kotlinmvp.BaseMvpView

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
interface MainContract {
    interface View: BaseMvpView
    interface Presenter: BaseMvpPresenter<View>
}