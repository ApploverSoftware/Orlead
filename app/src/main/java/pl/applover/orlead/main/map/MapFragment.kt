package pl.applover.orlead.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_map.*
import pl.applover.orlead.MapBV
import pl.applover.orlead.MapP
import pl.applover.orlead.MapV
import pl.applover.orlead.R
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