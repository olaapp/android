package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AccountInfoRequestModel(
        @SerializedName("ppid")
        val ppid: String = ""
)