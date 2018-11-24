package pl.applover.orlead.main

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Location
import android.location.LocationManager
import pl.applover.orlead.DelegatesExt
import pl.applover.orlead.DelegatesExt.notNullSingleValue
import pl.applover.orlead.main.di.AppComponent
import pl.applover.orlead.main.di.DaggerAppComponent


/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        instance = this
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation(): Location? {
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }


    companion object {
        var appComponent: AppComponent? = null
        var instance: MainApp by notNullSingleValue()
        private var locationManager: LocationManager by DelegatesExt.notNullSingleValue()
    }
}