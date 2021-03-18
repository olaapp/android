package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class MainTripFromChildModel(
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("date_trip")
    var dateTrip: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("status_trip")
    var statusTrip: String?,
    @SerializedName("time_trip")
    var timeTrip: String?,
    @SerializedName("trip_id")
    var tripId: String?,
    @SerializedName("user_id")
    var userId: String?,
    @SerializedName("canlover_id")
    var canlover_id: String?
)