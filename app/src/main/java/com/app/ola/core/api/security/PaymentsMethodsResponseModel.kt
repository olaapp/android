package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName
data class PaymentsMethodsResponseModel(
    @SerializedName("brand_id")
    var brandId: String,
    @SerializedName("friendly_code")
    var friendlyCode: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("is_default")
    var isDefault: String,
    @SerializedName("status")
    var status: String
)
