package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class RegisterMailPwd(
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("last_name")
    var last_name: String?,
    @SerializedName("provider")
    var provider: String?,
    @SerializedName("firebase_uuid")
    var firebase_uuid: String?,
    @SerializedName("push_tok")
    var push_tok: String?,
    @SerializedName("phone_language")
    var phone_language: String?

)