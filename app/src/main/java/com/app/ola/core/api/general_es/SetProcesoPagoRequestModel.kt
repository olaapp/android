package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class SetProcesoPagoRequestModel(
    @SerializedName("idGate")
    var idGate: String?,
    @SerializedName("id_usuario")
    var idUsuario: String?,
    @SerializedName("nic")
    var nic: String?
)