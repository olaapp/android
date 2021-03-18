package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class PowerOutgagesModel(
    @SerializedName("data")
    var `data`: List<DataPowerOutgages>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataPowerOutgages(
    @SerializedName("affected_zones")
    var affectedZones: String?,
    @SerializedName("business_id")
    var businessId: String?,
    @SerializedName("datetime_event")
    var datetimeEvent: String?,
    @SerializedName("id")
    var id: Any?,
    @SerializedName("lat")
    var lat: String?,
    @SerializedName("lng")
    var lng: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("type")
    var type: String?
)