package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class AppointmentsTrxModel(
    @SerializedName("data")
    var `data`: List<DataTRX>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataTRX(
    @SerializedName("Descripcion")
    var descripcion: String?,
    @SerializedName("ID_Transaccion")
    var iDTransaccion: String?
)