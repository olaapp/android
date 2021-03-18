package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class AppliancesModel(
    @SerializedName("data")
    var `data`: List<DataAppliances>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataAppliances(
    @SerializedName("code")
    var code: String?,
    @SerializedName("created_at")
    var createdAt: Any?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("kw_h")
    var kwH: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("pic_url")
    var picUrl: String?
)