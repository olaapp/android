package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class UpdateDogRequestModel(
        @SerializedName("pdes")
        val pdes: String = "",
        @SerializedName("pbree")
        val pbree: String = "",
        @SerializedName("pbir")
        val pbir: String = "",
        @SerializedName("pnam")
        val pnam: String = "",
        @SerializedName("pid")
        val pid: String = ""
)