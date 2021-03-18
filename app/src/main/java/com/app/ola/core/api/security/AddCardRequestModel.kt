package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AddCardRequestModel(

        @SerializedName("cnum") val cnum: String = "",
        @SerializedName("ccv") val ccv: String = "",
        @SerializedName("validtru") val validtru: String = "",
        @SerializedName("fcode") val fcode: String = "",
        @SerializedName("brandid") val brandid: String = "",
        @SerializedName("pid") val pid: String = ""

)