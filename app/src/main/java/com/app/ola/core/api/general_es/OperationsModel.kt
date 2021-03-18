package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class OperationsModel(
    @SerializedName("data")
    var `data`: List<DataOPX>?,
    @SerializedName("ErrorCode")
    var errorCode: String?,
    @SerializedName("ErrorMsg")
    var errorMsg: String?
)

data class DataOPX(
    @SerializedName("descripcion")
    var descripcion: String?,
    @SerializedName("tipo_gestion")
    var tipoGestion: String?
)