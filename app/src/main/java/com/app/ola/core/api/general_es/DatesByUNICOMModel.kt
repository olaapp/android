package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class DatesByUNICOM(
    @SerializedName("data")
    var `data`: List<DataDates>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataDates(
    @SerializedName("day")
    var day: String?,
    @SerializedName("fecha")
    var fecha: String?,
    @SerializedName("month")
    var month: String?,
    @SerializedName("year")
    var year: String?
)