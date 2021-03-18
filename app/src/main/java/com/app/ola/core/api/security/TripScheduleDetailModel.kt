package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class TripScheduleDetailModel(
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
    var tripId: String?
)