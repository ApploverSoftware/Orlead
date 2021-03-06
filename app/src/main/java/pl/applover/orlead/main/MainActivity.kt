package pl.applover.orlead.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import pl.applover.orlead.MainActivityBV
import pl.applover.orlead.MainActivityP
import pl.applover.orlead.MainActivityV
import pl.applover.orlead.R
import pl.applover.orlead.main.vehicle.VehicleFragment

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
class MainActivity : MainActivityBV(), MainActivityV {

    override var mPresenter: MainActivityP = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        display(VehicleFragment.newInstance())
    }


    override fun getContext(): Context? {
        return baseContext
    }

    override fun display(fragment: Fragment, into: Int?, push: Boolean, animIn: Int?, animOut: Int?, tag: String?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun proceedToActivity(intent: Intent) {
        //empty
    }

    companion object {
        const val PERMISSIONS_REQUEST = 420
    }
}