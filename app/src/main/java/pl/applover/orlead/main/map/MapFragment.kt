package pl.applover.orlead.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import kotlinx.android.synthetic.main.fragment_map.*
import pl.applover.orlead.*
import pl.applover.orlead.main.utils.GoogleMapPadding

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
class MapFragment : MapBV(), MapV {
    override var mPresenter: MapP = MapPresenter()

    private var mOnMapViewReadyListener: OnMapViewReadyListener? = null
    val googleMapPadding: GoogleMapPadding? by lazy { arguments?.getParcelable<GoogleMapPadding>("googleMapPadding") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.initializeMap(savedInstanceState, google_map_view, googleMapPadding)
    }

    override fun onResume() {
        super.onResume()
        google_map_view.onResume()
    }

    override fun onPause() {
        super.onPause()
        google_map_view.onPause()
    }

    override fun onDestroy() {
        google_map_view?.onDestroy()
        super.onDestroy()
    }

    override fun onMapViewReady(googleMap: GoogleMap) {
        mOnMapViewReadyListener?.onMapViewReady(googleMap)

        val polyline = getArrayOfLatLongs(
            52.594993, 19.650641,
            52.594899, 19.653507,
            52.593065, 19.653447,
            52.592736, 19.656857,
            52.591277, 19.656769,
            52.591179, 19.662670,
            52.591114, 19.668560,
            52.589363, 19.668452,
            52.589259, 19.673345,
            52.589233, 19.679310,
            52.588094, 19.679241,
            52.588016, 19.685743, 52.587996, 19.689989
        )
        mPresenter.drawRoute(polyline, R.color.colorAccent)
    }

    override fun onPolylineClick(polyline: Polyline?) {
        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun setCameraPosition(latLng: LatLng) {
        mPresenter.setCameraPosition(latLng)
    }

    fun setCameraPosition(latLngs: ArrayList<LatLng>, animateCamera: Boolean = false, padding: Int = 10) {
        mPresenter.setCameraPosition(latLngs, animateCamera, padding)
    }

    fun getLastGpsPosition(): LatLng? {
        return mPresenter.getLastGpsPosition()
    }

    interface OnMapViewReadyListener {
        fun onMapViewReady(googleMap: GoogleMap)
    }

    companion object {
        fun newInstance(googleMapPadding: GoogleMapPadding? = null): MapFragment {
            val fragment = MapFragment()
            with(Bundle()) {
                googleMapPadding?.let {
                    putParcelable("googleMapPadding", googleMapPadding)
                }
                fragment.arguments = this
            }
            return fragment
        }
    }
}