package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class UpdateTokenResponse(
    @SerializedName("data")
    var `data`: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)