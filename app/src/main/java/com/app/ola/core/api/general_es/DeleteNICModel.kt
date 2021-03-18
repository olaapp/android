package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class DeleteNICModel(
    @SerializedName("data")
    var `data`: Int?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)