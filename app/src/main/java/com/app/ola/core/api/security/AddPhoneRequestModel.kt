package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AddPhoneRequestModel(

        @SerializedName("phone") val content: String = "",
        @SerializedName("ppid") val lat: String = ""


)