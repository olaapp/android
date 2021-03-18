package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class ActiveTripsModel(
    @SerializedName("address_id_selected")
    var addressIdSelected: String?,
    @SerializedName("breed")
    var breed: String?,
    @SerializedName("canlover_id")
    var canloverId: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("date_trip")
    var dateTrip: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("dog_id")
    var dogId: String?,
    @SerializedName("dt_birthday")
    var dtBirthday: String?,
    @SerializedName("dt_requested")
    var dtRequested: String?,
    @SerializedName("dt_start")
    var dtStart: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("payment_method")
    var paymentMethod: String?,
    @SerializedName("phone_id_selected")
    var phoneIdSelected: String?,
    @SerializedName("plan_id")
    var planId: String?,
    @SerializedName("request_id")
    var requestId: String?,
    @SerializedName("start_date")
    var startDate: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("status_trip")
    var statusTrip: String?,
    @SerializedName("time_requested")
    var timeRequested: String?,
    @SerializedName("time_trip")
    var timeTrip: String?,
    @SerializedName("trip_days")
    var tripDays: String?,
    @SerializedName("trip_duration")
    var tripDuration: String?,
    @SerializedName("trip_id")
    var tripId: String?,
    @SerializedName("url_pic")
    var urlPic: String?,
    @SerializedName("user_id")
    var userId: String?
)