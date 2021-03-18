package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class GetLecturaAnteriorResponseModel(
    @SerializedName("data")
    var `data`: DataLecturaAnterior?,
    @SerializedName("ErrorCode")
    var errorCode: String?,
    @SerializedName("ErrorMsg")
    var errorMsg: String?
)

data class DataLecturaAnterior(
    @SerializedName("consumo")
    var consumo: String="",
    @SerializedName("descripcion")
    var descripcion: String="",
    @SerializedName("fecha_fin")
    var fechaFin: String="",
    @SerializedName("fecha_inicio")
    var fechaInicio: String=""
)