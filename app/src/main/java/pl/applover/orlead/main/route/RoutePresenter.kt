package pl.applover.orlead.main.route

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.applover.orlead.RouteBP
import pl.applover.orlead.RouteP
import pl.applover.orlead.di.OrleadAPI
import pl.applover.orlead.main.MainApp
import javax.inject.Inject

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 25/11/2018.
 */
class RoutePresenter : RouteBP(), RouteP {
    @Inject
    lateinit var mApi: OrleadAPI

    init {
        MainApp.appComponent?.inject(this)
    }

    @SuppressLint("CheckResult")
    override fun findRoute(
        vehicle_id: String,
        node_id: String,
        place_id: String
    ) {
        mApi.getPath(vehicle_id, node_id, place_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                mView?.routeFound()
        }, {
            it.printStackTrace()
            mView?.onError()
        })
    }
}