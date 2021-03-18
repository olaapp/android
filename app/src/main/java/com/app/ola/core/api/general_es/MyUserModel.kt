package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class MyUserModel(
    @SerializedName("data")
    var `data`: List<DataUser>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataUser(
    @SerializedName("birthday")
    var birthday: Any?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("firebase_uuid")
    var firebaseUuid: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("lang")
    var lang: String?,
    @SerializedName("last_names")
    var lastNames: String?,
    @SerializedName("last_seen")
    var lastSeen: String?,
    @SerializedName("names")
    var names: String?,
    @SerializedName("phone_number")
    var phoneNumber: Any?,
    @SerializedName("pic_url")
    var picUrl: String?,
    @SerializedName("provider")
    var provider: String?,
    @SerializedName("push_tok")
    var pushTok: String?,
    @SerializedName("status_phone_number")
    var statusPhoneNumber: Boolean?
)