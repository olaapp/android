package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class OperationsResponse1Model(
    @SerializedName("code")
    var code: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)