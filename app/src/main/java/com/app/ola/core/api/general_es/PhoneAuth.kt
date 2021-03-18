package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class PhoneAuth(
    @SerializedName("firebase_uuid")
    var firebaseUuid: String?,
    @SerializedName("phone_language")
    var phoneLanguage: String?,
    @SerializedName("phone_number")
    var phoneNumber: String?,
    @SerializedName("push_tok")
    var pushTok: String?
)