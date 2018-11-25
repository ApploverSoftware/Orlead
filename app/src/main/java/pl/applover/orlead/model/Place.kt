package pl.applover.orlead.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class Place(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("long")
    var long: Double?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("road_id")
    var roadId: Int?,
    @SerializedName("updated_at")
    var updatedAt: String?
) : AutoParcelable