package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName

data class NewNSUpdateResponseModel(
    @SerializedName("field")
    var `field`: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("value")
    var value: String?
)