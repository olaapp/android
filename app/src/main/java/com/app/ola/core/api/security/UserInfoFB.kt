package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class UserInfoFB(
    @SerializedName("email")
    var email: String?,
    @SerializedName("fireid")
    var fireid: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("mid")
    var mid: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("picurl")
    var picurl: String?
)