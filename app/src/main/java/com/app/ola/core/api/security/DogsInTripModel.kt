package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName
data class DogsInTripModel(
    @SerializedName("breed")
    var breed: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("dt_birthday")
    var dtBirthday: String?,
    @SerializedName("dt_start")
    var dtStart: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("url_pic")
    var urlPic: String?,
    @SerializedName("user_id")
    var userId: String?
)