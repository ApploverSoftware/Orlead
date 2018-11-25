package pl.applover.orlead.main.route

import android.os.Bundle
import android.support.v7.widget.ListPopupWindow
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_route.*
import pl.applover.orlead.R
import pl.applover.orlead.RouteBV
import pl.applover.orlead.RouteP
import pl.applover.orlead.RouteV
import pl.applover.orlead.main.map.MapFragment
import pl.applover.orlead.model.ResponseVehicles

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 25/11/2018.
 */
class RouteFragment : RouteBV(), RouteV {
    override var mPresenter: RouteP = RoutePresenter()

    lateinit var destinationPlacesPopup: ListPopupWindow
    lateinit var gatePopup: ListPopupWindow
    val responseVehicles: ResponseVehicles? by lazy { arguments?.getParcelable<ResponseVehicles>("responseVehicles") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDestinationDropdown()
        setGateDropdown()
        routeProceedButton.setOnClickListener {
            if (destinationPlace.text.toString().toLowerCase() != "destination place" &&
                enterGate.text.toString().toLowerCase() != "enter gate"
            ) {
                routeProgress.visibility = View.VISIBLE
                val place =
                    responseVehicles?.places?.find { it.name?.toLowerCase() == destinationPlace.text.toString().toLowerCase() }
                val node =
                    responseVehicles?.gates?.find { it.name?.toLowerCase() == enterGate.text.toString().toLowerCase() }
                mPresenter.findRoute(
                    responseVehicles!!.vehicle!!.id!!.toString(),
                    node!!.id!!.toString(),
                    place!!.id!!.toString()
                )
            } else {
                Toast.makeText(context, R.string.choose_route, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun routeFound() {
        navigator?.display(MapFragment.newInstance())
    }

    override fun onError() {
        routeProgress.visibility = View.INVISIBLE
        Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
    }

    private fun setDestinationDropdown() {
        destinationPlacesPopup = ListPopupWindow(context!!)
        responseVehicles?.places?.let {
            val adapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, it.map { it.name })
            destinationPlacesPopup.setAdapter(adapter)
            with(destinationPlacesPopup) {
                anchorView = destinationPlace
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                width = destinationPlacesPopup.width
                isModal = true
                setOnItemClickListener { _, _, i, _ ->
                    destinationPlace.text = adapter.getItem(i) as String
                    dismiss()
                }
            }
        } ?: kotlin.run {
            val adapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, arrayListOf("Heaven", "Hell"))

            destinationPlacesPopup.setAdapter(adapter)
            with(destinationPlacesPopup) {
                anchorView = destinationPlace
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                width = destinationPlacesPopup.width
                isModal = true
                setOnItemClickListener { _, _, i, _ ->
                    destinationPlace.text = adapter.getItem(i) as String
                    dismiss()
                }
            }
        }

        destinationPlace.setOnClickListener {
            destinationPlacesPopup.show()
        }
    }

    private fun setGateDropdown() {
        gatePopup = ListPopupWindow(context!!)

        responseVehicles?.let {
            it?.gates?.let {
                val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, it.map { it.name })
                gatePopup.setAdapter(adapter)
                with(gatePopup) {
                    anchorView = enterGate
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
                    width = gatePopup.width
                    isModal = true
                    setOnItemClickListener { _, _, i, _ ->
                        enterGate.text = adapter.getItem(i) as String
                        dismiss()
                    }
                }
            }
        } ?: kotlin.run {
            val adapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, arrayListOf("Heaven", "Hell"))
            gatePopup.setAdapter(adapter)
            with(gatePopup) {
                anchorView = enterGate
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                width = gatePopup.width
                isModal = true
                setOnItemClickListener { _, _, i, _ ->
                    enterGate.text = adapter.getItem(i) as String
                    dismiss()
                }
            }
        }

        enterGate.setOnClickListener {
            gatePopup.show()
        }
    }

    companion object {
        fun newInstance(responseVehicles: ResponseVehicles? = null): RouteFragment {
            val fragment = RouteFragment()
            with(Bundle()) {
                responseVehicles?.let {
                    putParcelable("responseVehicles", it)
                }
                fragment.arguments = this
            }
            return fragment
        }
    }
}