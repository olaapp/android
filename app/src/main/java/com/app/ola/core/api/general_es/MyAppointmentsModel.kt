package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class MyAppointmentsModel(
    @SerializedName("data")
    var `data`: List<DataMyAppointments>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataMyAppointments(
    @SerializedName("appointment_at")
    var appointmentAt: String?,
    @SerializedName("appointment_at_time")
    var appointmentAtTime: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("open_code")
    var openCode: Any?,
    @SerializedName("place_id")
    var placeId: Int?,
    @SerializedName("trx_id")
    var trxId: Int?
)