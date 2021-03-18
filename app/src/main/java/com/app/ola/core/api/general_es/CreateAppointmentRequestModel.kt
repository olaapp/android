package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class CreateAppointmentRequestModel(
    @SerializedName("date")
    var date: String?,
    @SerializedName("nic")
    var nic: String?,
    @SerializedName("place_id")
    var placeId: String?,
    @SerializedName("time_id")
    var timeId: String?,
    @SerializedName("transaction_id")
    var transactionId: String?,
    @SerializedName("times")
    var times: String?
)