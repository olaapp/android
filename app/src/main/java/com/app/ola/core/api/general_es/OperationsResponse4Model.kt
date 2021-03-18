package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class OperationResponse4Model(
    @SerializedName("code")
    var code: String?,
    @SerializedName("history_url")
    var historyUrl: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)