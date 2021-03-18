package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AddCardResponseModel(
    @SerializedName("brandid")
    var brandid: String,
    @SerializedName("ccv")
    var ccv: String,
    @SerializedName("cnum")
    var cnum: String,
    @SerializedName("fcode")
    var fcode: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("pid")
    var pid: String,
    @SerializedName("validtru")
    var validtru: String
)