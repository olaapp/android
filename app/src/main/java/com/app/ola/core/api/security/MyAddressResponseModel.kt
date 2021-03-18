package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class MyAddressResponseModel(
    @SerializedName("add_ref")
    var addRef: String?,
    @SerializedName("addr_content")
    var addrContent: String?,
    @SerializedName("addr_lat")
    var addrLat: String?,
    @SerializedName("addr_long")
    var addrLong: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("is_default")
    var isDefault: String?,
    @SerializedName("user_id")
    var userId: String?
)