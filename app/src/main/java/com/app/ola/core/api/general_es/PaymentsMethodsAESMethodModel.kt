package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class PaymentsMethodsAESMethodModel(
    @SerializedName("data")
    var `data`: List<DataPM>?,
    @SerializedName("ErrorCode")
    var errorCode: String?,
    @SerializedName("ErrorMsg")
    var errorMsg: String?
)

data class DataPM(
    @SerializedName("idGate")
    var idGate: String?,
    @SerializedName("Nombre")
    var nombre: String?
)