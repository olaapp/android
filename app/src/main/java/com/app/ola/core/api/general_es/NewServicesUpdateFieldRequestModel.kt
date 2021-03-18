package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class NewServicesUpdateFieldRequestModel(
    @SerializedName("field")
    var `field`: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("value")
    var value: String?
)