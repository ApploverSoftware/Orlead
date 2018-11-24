package pl.applover.orlead.main.map

import android.content.Context
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import pl.applover.kotlinmvp.BaseMvpPresenter
import pl.applover.kotlinmvp.BaseMvpView
import pl.applover.orlead.main.MainApp
import pl.applover.orlead.main.utils.GoogleMapPadding

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
interface MapContract {
    interface View : BaseMvpView {
        fun onMapViewReady(googleMap: GoogleMap)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initializeMap(savedInstanceState: Bundle?, mMapView: MapView, googleMapPadding: GoogleMapPadding?)
        fun setCameraPosition(latLng: LatLng)
        fun getLastGpsPosition(): LatLng?
        fun setOnMapClickListener(onMapClickListener: GoogleMap.OnMapClickListener)
        fun setCameraPosition(latLngs: ArrayList<LatLng>, animateCamera: Boolean = false, padding: Int = 10)
        fun setCameraListener(listener: GoogleMap.OnCameraMoveListener?)
        fun setOnMarkerDragListener(onMarkerDragListener: GoogleMap.OnMarkerDragListener)
        fun setRangeCircle(circleOptions: CircleOptions): Circle
        fun drawRoute(
            latLngs: ArrayList<LatLng>,
            colorRid: Int,
            width: Float = 8f,
            context: Context = MainApp.instance
        ): Polyline
    }
}