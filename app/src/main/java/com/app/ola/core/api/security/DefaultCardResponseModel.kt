package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class DefaultCardResponseModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("friendly_code")
    var friendly_code: String,
    @SerializedName("brand_id")
    var brand_id: String
)