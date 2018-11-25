package pl.applover.orlead.main.vehicle

import pl.applover.kotlinmvp.BaseMvpPresenter
import pl.applover.kotlinmvp.BaseMvpView
import pl.applover.orlead.model.ResponseVehicles

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
interface VehicleContract {
    interface View : BaseMvpView{
        fun noRouteDefined()
        fun routeDefined(vehicles: ResponseVehicles)
        fun onError()
    }
    interface Presenter : BaseMvpPresenter<View>{
        fun postVehicle()
        fun getVehicle(plate: String)
    }
}