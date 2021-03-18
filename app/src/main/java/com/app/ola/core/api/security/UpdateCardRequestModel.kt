package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class UpdateCardRequestModel(
        @SerializedName("pid_new")
        val pid_new: String = "",
        @SerializedName("pid_old")
        val pid_old: String = ""
)