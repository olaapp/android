package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class GeneralRequestModel(
        @SerializedName("ppid") val key: String = ""
)