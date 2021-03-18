package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class NewAppointmentResponseModel(
    @SerializedName("appointment_id")
    var appointmentId: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)