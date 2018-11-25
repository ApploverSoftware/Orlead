package pl.applover.orlead.model

import android.annotation.SuppressLint
import io.mironov.smuggler.AutoParcelable

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 25/11/2018.
 */
@SuppressLint("ParcelCreator")
data class Vehicle(
    val plate_number: String,
    var weight: Int? = null,
    var width: Int? = null,
    var height: Int? = null,
    var length: Int? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    val id: Int? = null
) : AutoParcelable