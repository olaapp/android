package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class AddNICSModel(
    @SerializedName("alias")
    var alias: String?,
    @SerializedName("nic")
    var nic: String?,
    @SerializedName("validation_document")
    var validationDocument: String?
)