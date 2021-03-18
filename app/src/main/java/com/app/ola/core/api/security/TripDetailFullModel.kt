package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class TripDetailFullModel(
    @SerializedName("canlover_name")
    var canloverName: String?,
    @SerializedName("canlover_pic")
    var canloverPic: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("date_trip")
    var dateTrip: String?,
    @SerializedName("default_address")
    var defaultAddress: String?,
    @SerializedName("default_phone")
    var defaultPhone: String?,
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
    @SerializedName("trip_time")
    var tripTime: String?,
    @SerializedName("url_pic")
    var urlPic: String?,
    @SerializedName("user_id")
    var userId: String?
)