package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("dt_start")
    var dtStart: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("firebase_id")
    var firebaseId: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("messaging_id")
    var messagingId: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("url_picture")
    var urlPicture: String?,
    @SerializedName("dt_birth")
    var dt_birth: String?

)