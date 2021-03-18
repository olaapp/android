package com.app.ola.environment.main.ui
import com.google.gson.annotations.SerializedName


data class Users(
        @SerializedName("active")
        var active: Int?,
        @SerializedName("age")
        var age: String?,
        @SerializedName("chats")
        var chats: String?,
        @SerializedName("city")
        var city: String?,
        @SerializedName("country")
        var country: String?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("gender")
        var gender: String?,
        @SerializedName("lastname")
        var lastname: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("online")
        var online: String?,
        @SerializedName("phone_number")
        var phone_number: String?,
        @SerializedName("photo")
        var photo: String?
)