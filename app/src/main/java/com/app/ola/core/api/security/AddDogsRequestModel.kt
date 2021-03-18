package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AddDogsRequestModel(
    @SerializedName("req_id")
    var req_id: String,
    @SerializedName("dog_id")
    var dog_id: String
)