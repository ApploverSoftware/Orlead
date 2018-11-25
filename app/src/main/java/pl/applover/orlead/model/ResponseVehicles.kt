package pl.applover.orlead.model

import android.annotation.SuppressLint
import io.mironov.smuggler.AutoParcelable

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 25/11/2018.
 */
@SuppressLint("ParcelCreator")
data class ResponseVehicles(
    val vehicle: Vehicle,
    val gates: ArrayList<Gate>? = null,
    val places: ArrayList<Place>? = null
) : AutoParcelable