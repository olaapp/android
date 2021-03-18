package com.app.ola.core.api.users

import com.google.gson.annotations.SerializedName

data class CreateUserResponseModel(

        @SerializedName("name")
        var name: String,

        @SerializedName("fireid")
        var fireid: String,

        @SerializedName("phone")
        var phone: String,

        @SerializedName("mid")
        var mid: String,

        @SerializedName("email")
        var email: String,

        @SerializedName("profilepic")
        var profilepic: String,

        @SerializedName("id") var id: Int = 0
)