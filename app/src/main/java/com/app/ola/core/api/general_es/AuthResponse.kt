package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class AuthResponse(
    @SerializedName("auth_token")
    var authToken: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)