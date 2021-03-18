package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AddAddressRequestModel(

        @SerializedName("content") val content: String = "",
        @SerializedName("lat") val lat: String = "",
        @SerializedName("lng") val lng: String = "",
        @SerializedName("ref") val ref: String = "",
        @SerializedName("ppid") val ppid: String = "",
        @SerializedName("isdef") val isdef: String = ""

)