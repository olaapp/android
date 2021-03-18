package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class FindPOByNICRequestModel(
    @SerializedName("nic")
    var nic: String?
)