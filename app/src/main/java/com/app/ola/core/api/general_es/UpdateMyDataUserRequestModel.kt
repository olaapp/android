package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class UpdateMyDataUserRequestModel(
    @SerializedName("birthday")
    var birthday: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("last_names")
    var lastNames: String?,
    @SerializedName("names")
    var names: String?,
    @SerializedName("phone_number")
    var phoneNumber: String?,
    @SerializedName("pic_url")
    var picUrl: String?,
    @SerializedName("push_tok")
    var pushTok: String?
)