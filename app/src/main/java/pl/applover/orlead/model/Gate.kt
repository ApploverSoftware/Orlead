package pl.applover.orlead.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class Gate(
    @SerializedName("carrying_cap")
    var carryingCap: Int?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("gate")
    var gate: Boolean?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("max_height")
    var maxHeight: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("width")
    var width: Int?
): AutoParcelable