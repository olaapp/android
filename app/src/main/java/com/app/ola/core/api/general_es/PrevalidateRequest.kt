package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class PrevalidateRequest(
    @SerializedName("email")
    var email: String?,
    @SerializedName("pw")
    var pw: String?
)