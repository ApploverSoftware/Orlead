package pl.applover.orlead

import android.content.Context
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import pl.applover.orlead.main.MainApp

fun GoogleMap.setCameraPosition(latLng: LatLng, animateCamera: Boolean = false) {
    val cameraPosition = CameraPosition.Builder().target(latLng).zoom(12f).build()
    if (animateCamera) {
        animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    } else {
        moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}

/**
 * Method needs at least one latLng
 */
fun GoogleMap.setCameraPosition(latLngs: ArrayList<LatLng>, animateCamera: Boolean = false, padding: Int = 10) {
    if (latLngs.isEmpty()) {
        return
    }

    val boundsBuilder = LatLngBounds.Builder()

    latLngs.forEach { latLng ->
        boundsBuilder.include(latLng)
    }

    val bounds = boundsBuilder.build()

    try {
        if (animateCamera) {
            animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
        } else {
            moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
        }
    } catch (e: Exception) {

    }
}


fun GoogleMap.setRangeCircle(circleOptions: CircleOptions): Circle {
    return addCircle(circleOptions)
}

fun GoogleMap.drawPolyline(latLngs: ArrayList<LatLng>, colorRid: Int, width: Float = 5f, context: Context = MainApp.instance): Polyline {
    return addPolyline(PolylineOptions()
            .addAll(latLngs)
            .width(width)
            .color(ContextCompat.getColor(context, colorRid)))
}

fun getLastLocation(): LatLng? {
    val location = MainApp.instance.getLastLocation()
    location?.let {
        return LatLng(location.latitude, location.longitude)
    } ?: run {
        return null
    }
}