package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class DefaultPhonesResponseModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("phone_number")
    var phone_number: String
)