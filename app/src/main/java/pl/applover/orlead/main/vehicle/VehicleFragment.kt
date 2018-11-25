package pl.applover.orlead.main.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_vehicle.*
import pl.applover.orlead.R
import pl.applover.orlead.VehicleBV
import pl.applover.orlead.VehicleP
import pl.applover.orlead.VehicleV

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
        postVehicle.setOnClickListener(this)
        getVehicle.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.getVehicle -> {
                mPresenter.getVehicle()
            }
            R.id.postVehicle -> {
                mPresenter.postVehicle()
            }
        }
    }

    companion object {
        fun newInstance(): VehicleFragment {
            return VehicleFragment()
        }
    }
}