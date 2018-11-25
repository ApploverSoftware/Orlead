package pl.applover.orlead.main.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_vehicle.*
import pl.applover.orlead.R
import pl.applover.orlead.VehicleBV
import pl.applover.orlead.VehicleP
import pl.applover.orlead.VehicleV
import pl.applover.orlead.main.route.RouteFragment
import pl.applover.orlead.model.ResponseVehicles

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
class VehicleFragment : VehicleBV(), VehicleV, View.OnClickListener {
    override var mPresenter: VehicleP = VehiclePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vehicle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        proceedButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.proceedButton -> {
                if (placeNumberEditText.text.toString().isNotEmpty()) {
                    leadingProgress.visibility = View.VISIBLE
                    mPresenter.getVehicle(placeNumberEditText.text.toString().toUpperCase())
                } else
                    Toast.makeText(context, R.string.type_plate, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun noRouteDefined() {
        navigator?.display(RouteFragment.newInstance())
    }

    override fun routeDefined(vehicles: ResponseVehicles) {
        navigator?.display(RouteFragment.newInstance(vehicles))
    }

    override fun onError() {
        leadingProgress.visibility = View.INVISIBLE
        Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): VehicleFragment {
            return VehicleFragment()
        }
    }
}