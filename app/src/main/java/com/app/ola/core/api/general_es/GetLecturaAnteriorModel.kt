package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class GetLecturaAnteriorModel(
    @SerializedName("id_usuario")
    var idUsuario: String?,
    @SerializedName("lectura")
    var lectura: String?,
    @SerializedName("nic")
    var nic: String?,
    @SerializedName("Token")
    var token: String?
)