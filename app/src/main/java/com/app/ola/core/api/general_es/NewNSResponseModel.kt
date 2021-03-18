package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class NewNSResponseModel(
    @SerializedName("request_code")
    var request_code: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)