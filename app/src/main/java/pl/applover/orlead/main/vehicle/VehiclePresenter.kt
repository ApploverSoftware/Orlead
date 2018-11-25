package pl.applover.orlead.main.vehicle

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.applover.orlead.VehicleBP
import pl.applover.orlead.VehicleP
import pl.applover.orlead.di.OrleadAPI
import pl.applover.orlead.main.MainApp
import pl.applover.orlead.model.RequestVehicle
import pl.applover.orlead.model.Vehicle
import javax.inject.Inject

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
@SuppressLint("CheckResult")
class VehiclePresenter : VehicleBP(), VehicleP {

    @Inject
    lateinit var mApi: OrleadAPI

    init {
        MainApp.appComponent?.inject(this)
    }

    override fun postVehicle() {
        mApi.postVehicle(RequestVehicle(Vehicle("KWA2137", 2500, 205, 190))).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                Log.d("PostVehicle", "Success")
            }, {
                it.printStackTrace()
            })
    }

    override fun getVehicle() {
        mApi.getVehicle("KWA2137").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                Log.d("GetVehicle", "Success")
            }, {
                it.printStackTrace()
            })
    }
}