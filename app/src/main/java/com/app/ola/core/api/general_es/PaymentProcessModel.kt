package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class PaymentProcessModel(
    @SerializedName("data")
    var `data`: String?,
    @SerializedName("ErrorCode")
    var errorCode: String?,
    @SerializedName("ErrorMsg")
    var errorMsg: String?
)