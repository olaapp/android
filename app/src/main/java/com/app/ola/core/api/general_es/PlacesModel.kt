package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class PlacesModel(
    @SerializedName("data")
    var `data`: List<DataPlaces> = emptyList(),
    @SerializedName("message")
    var message: String = "",
    @SerializedName("status")
    var status: String = ""
)

data class DataPlaces(
    @SerializedName("address")
    var address: String = "",
    @SerializedName("allow_reservations")
    var allowReservations: Boolean = false,
    @SerializedName("business_sched_1")
    var businessSched1: String = "",
    @SerializedName("business_sched_2")
    var businessSched2: String = "",
    @SerializedName("created_at")
    var createdAt: Any  = "",
    @SerializedName("depto_id")
    var deptoId: Int = 0,
    @SerializedName("depto_name")
    var deptoName: String = "",
    @SerializedName("id")
    var id: Int=0,
    @SerializedName("lat")
    var lat: String = "",
    @SerializedName("lng")
    var lng: String = "",
    @SerializedName("muni_id")
    var muniId: Int=0,
    @SerializedName("muni_name")
    var muniName: String = "",
    @SerializedName("phone_number")
    var phoneNumber: String = "",
    @SerializedName("pic_url")
    var picUrl: String = "",
    @SerializedName("services")
    var services: List<Service> = emptyList(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("distance")
    var distance: String = "",
    @SerializedName("cod_unicom")
    var cod_unicom: String = ""
)

data class Service(
    @SerializedName("id")
    var id: Int=0,
    @SerializedName("pic_url")
    var picUrl: String = "",
    @SerializedName("title")
    var title: String = ""
)