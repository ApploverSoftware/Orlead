package pl.applover.orlead.main.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import pl.applover.orlead.*
import pl.applover.orlead.main.MainApp
import pl.applover.orlead.main.utils.GoogleMapPadding
import java.util.*

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
class MapPresenter : MapBP(), MapP {

    private var googleMap: GoogleMap? = null

    @SuppressLint("MissingPermission")
    override fun initializeMap(savedInstanceState: Bundle?, mMapView: MapView, googleMapPadding: GoogleMapPadding?) {
        try {
            MapsInitializer.initialize(MainApp.instance)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView.onCreate(savedInstanceState)
        mMapView.onResume() // needed to get the map to display immediately
        mMapView.getMapAsync { mMap ->
            googleMap = mMap
            mView?.let {
                googleMap!!.isMyLocationEnabled = true
                //googleMap!!.setMapStyle(MapStyleOptions.loadRawResourceStyle(mView?.getContext()!!,R.raw.map_style))
                mView?.onMapViewReady(googleMap!!)
                setCameraPosition(LatLng(52.588091, 19.679209))
            }
        }
    }

    override fun setOnMarkerDragListener(onMarkerDragListener: GoogleMap.OnMarkerDragListener) {
        googleMap!!.setOnMarkerDragListener(onMarkerDragListener)
    }

    override fun setCameraPosition(latLng: LatLng) {
        googleMap!!.setCameraPosition(latLng)
    }

    override fun setCameraPosition(latLngs: ArrayList<LatLng>, animateCamera: Boolean, padding: Int) {
        googleMap!!.setCameraPosition(latLngs, animateCamera, padding)
    }

    override fun setCameraListener(listener: GoogleMap.OnCameraMoveListener?) {
        googleMap!!.setOnCameraMoveListener(listener)
    }

    override fun setRangeCircle(circleOptions: CircleOptions): Circle {
        return googleMap!!.setRangeCircle(circleOptions)
    }

    override fun getLastGpsPosition(): LatLng? {
        return getLastLocation()
    }

    override fun drawRoute(latLngs: ArrayList<LatLng>, colorRid: Int, width: Float, context: Context): Polyline {
        return googleMap!!.drawPolyline(latLngs, colorRid, width, context)
    }

    override fun setOnMapClickListener(onMapClickListener: GoogleMap.OnMapClickListener) {
        googleMap!!.setOnMapClickListener(onMapClickListener)
    }

}