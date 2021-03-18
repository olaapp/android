package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AccountCreationFBRequestModel(
        @SerializedName("name")
        val name: String = "",
        @SerializedName("fireid")
        val fireid: String = "",
        @SerializedName("picurl")
        val picurl: String = "",
        @SerializedName("mid")
        val mid: String = "",
        @SerializedName("email")
        val email: String = ""
)