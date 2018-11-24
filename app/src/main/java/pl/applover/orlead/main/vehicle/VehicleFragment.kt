package pl.applover.orlead.main.vehicle

import pl.applover.orlead.VehicleBV
import pl.applover.orlead.VehicleP
import pl.applover.orlead.VehicleV

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
class VehicleFragment : VehicleBV(), VehicleV {
    override var mPresenter: VehicleP = VehiclePresenter()
}