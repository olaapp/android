package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class NewServicesRequestModel(
    @SerializedName("address")
    var address: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("conn_type")
    var connType: String?,
    @SerializedName("depto_id")
    var deptoId: String?,
    @SerializedName("ln")
    var ln: String?,
    @SerializedName("lt")
    var lt: String?,
    @SerializedName("muni_id")
    var muniId: String?,
    @SerializedName("nic")
    var nic: String?,
    @SerializedName("power")
    var power: String?,
    @SerializedName("service_type")
    var serviceType: String?,
    @SerializedName("user_email")
    var userEmail: String?
)