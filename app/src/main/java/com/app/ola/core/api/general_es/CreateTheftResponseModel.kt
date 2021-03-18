package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class CreateTheftResponseModel(
    @SerializedName("code")
    var code: String="",
    @SerializedName("id")
    var id: Int=0,
    @SerializedName("message")
    var message: String="",
    @SerializedName("status")
    var status: String=""
)