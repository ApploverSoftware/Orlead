package pl.applover.orlead.main.route

import pl.applover.kotlinmvp.BaseMvpPresenter
import pl.applover.kotlinmvp.BaseMvpView

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 25/11/2018.
 */
interface RouteContract {
    interface View : BaseMvpView {
        fun routeFound()
        fun onError()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun findRoute(
            vehicle_id: String,
            node_id: String,
            place_id: String
        )
    }
}