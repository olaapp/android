package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName
//DogsInTripModel
data class TripsOfADogModel(
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("date_trip")
    var dateTrip: String?,
    @SerializedName("dog_id")
    var dogId: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("status_trip")
    var statusTrip: String?,
    @SerializedName("time_trip")
    var timeTrip: String?,
    @SerializedName("trip_duration")
    var tripDuration: String?,
    @SerializedName("trip_id")
    var tripId: String?,
    @SerializedName("url_pic")
    var urlPic: String?,
    @SerializedName("user_id")
    var userId: String?
)