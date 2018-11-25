package pl.applover.orlead.model

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 25/11/2018.
 */
data class Vehicle(
    val plate_number: String,
    var weight: Int? = null,
    var width: Int? = null,
    var height: Int? = null,
    var length: Int? = null,
    var created_at: String? = null,
    var updated_at: String? = null
)