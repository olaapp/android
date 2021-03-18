package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class DefaultAddressResponseModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("addr_content")
    var addr_content: String
)