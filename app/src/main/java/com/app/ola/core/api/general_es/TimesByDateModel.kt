package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class TimesByDateModel(
    @SerializedName("data")
    var `data`: List<DataTimesByDate>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataTimesByDate(
    @SerializedName("fecha")
    var fecha: String?,
    @SerializedName("hora")
    var hora: String?,
    @SerializedName("id_horario")
    var idHorario: String?
)