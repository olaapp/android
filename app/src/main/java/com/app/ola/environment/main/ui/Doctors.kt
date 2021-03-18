package com.app.ola.environment.main.ui
import com.google.gson.annotations.SerializedName


data class Doctors(
        @SerializedName("id")
        var id: String?,
        @SerializedName("active")
        var active: String?,
        @SerializedName("alias")
        var alias: String?,
        @SerializedName("lastname")
        var lastname: String?,
        @SerializedName("medical_speciality")
        var medical_speciality: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("online")
        var online: String?,
        @SerializedName("photo")
        var photo: String?,
        @SerializedName("users")
        var users: List<Users>?
)