package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class DeleteNSRequestModel(
    @SerializedName("id")
    var id: String?
)