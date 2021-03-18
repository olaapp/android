package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class SocialAuth(
    @SerializedName("birthday")
    var birthday: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("firebase_uuid")
    var firebaseUuid: String?,
    @SerializedName("last_name")
    var lastName: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("phone_language")
    var phoneLanguage: String?,
    @SerializedName("phone_number")
    var phoneNumber: String?,
    @SerializedName("provider")
    var provider: String?,
    @SerializedName("push_tok")
    var pushTok: String?,
    @SerializedName("pic_url")
    var picURL: String?
)