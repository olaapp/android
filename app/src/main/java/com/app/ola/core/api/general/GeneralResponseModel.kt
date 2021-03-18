package com.app.ola.core.api.general

import com.google.gson.annotations.SerializedName

data class GeneralResponseModel(
        @SerializedName("message") val message: String = ""
)