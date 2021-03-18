package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class UpdateTokenModel(
    @SerializedName("push_token")
    var push_token: String?
)