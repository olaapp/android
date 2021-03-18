package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class myNewRequestsModel(
    @SerializedName("data")
    var `data`: List<DataNR>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataNR(
    @SerializedName("address")
    var address: String?,
    @SerializedName("cod_unicom")
    var codUnicom: String?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("conn_type")
    var connType: String?,
    @SerializedName("depto_id")
    var deptoId: Int?,
    @SerializedName("depto_name")
    var deptoName: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("lat")
    var lat: String?,
    @SerializedName("lng")
    var lng: String?,
    @SerializedName("muni_id")
    var muniId: Int?,
    @SerializedName("muni_name")
    var muniName: String?,
    @SerializedName("nic")
    var nic: Int?,
    @SerializedName("open_code")
    var openCode: String?,
    @SerializedName("power")
    var power: String?,
    @SerializedName("service_type")
    var serviceType: String?,
    @SerializedName("user_email")
    var userEmail: String?,
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("date_created")
    var date_created: String?
)