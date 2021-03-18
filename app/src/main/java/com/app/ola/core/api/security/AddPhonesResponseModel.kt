package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AddPhonesResponseModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("is_default")
    var isDefault: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("user_id")
    var userId: String
)