package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class MyNicsDataModel(
    @SerializedName("data")
    var `data`: List<DataNICS>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataNICS(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("csmo_data")
    var csmoData: List<CsmoData>?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("nic")
    var nic: Int?,
    @SerializedName("nic_address")
    var nicAddress: String?,
    @SerializedName("nic_lat")
    var nicLat: String?,
    @SerializedName("nic_lng")
    var nicLng: String?,
    @SerializedName("user_alias")
    var userAlias: Any?,
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("validation_document")
    var validationDocument: String?
)

data class CsmoData(
    @SerializedName("CSMO")
    var cSMO: String?,
    @SerializedName("F_FACT")
    var fFACT: String?
)