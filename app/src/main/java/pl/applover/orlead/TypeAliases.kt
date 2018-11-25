package pl.applover.orlead

import pl.applover.kotlinmvp.BaseActivity
import pl.applover.kotlinmvp.BaseFragment
import pl.applover.kotlinmvp.BasePresenter
import pl.applover.orlead.main.MainContract
import pl.applover.orlead.main.map.MapContract
import pl.applover.orlead.main.route.RouteContract
import pl.applover.orlead.main.vehicle.VehicleContract

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
typealias MainActivityV = MainContract.View

typealias MainActivityP = MainContract.Presenter
typealias MainActivityBV = BaseActivity<MainActivityV, MainActivityP>
typealias MainActivityBP = BasePresenter<MainActivityV>

typealias VehicleV = VehicleContract.View
typealias VehicleP = VehicleContract.Presenter
typealias VehicleBV = BaseFragment<VehicleV, VehicleP>
typealias VehicleBP = BasePresenter<VehicleV>

typealias MapV = MapContract.View
typealias MapP = MapContract.Presenter
typealias MapBV = BaseFragment<MapV, MapP>
typealias MapBP = BasePresenter<MapV>

typealias RouteV = RouteContract.View
typealias RouteP = RouteContract.Presenter
typealias RouteBV = BaseFragment<RouteV, RouteP>
typealias RouteBP = BasePresenter<RouteV>