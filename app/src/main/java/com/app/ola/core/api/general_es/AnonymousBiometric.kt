package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class AnonymousBiometric(
    @SerializedName("firebase_uuid")
    var firebaseUuid: String?,
    @SerializedName("phone_language")
    var phoneLanguage: String?,
    @SerializedName("push_tok")
    var pushTok: String?
)