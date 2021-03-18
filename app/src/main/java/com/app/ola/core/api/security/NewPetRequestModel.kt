package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class NewPetRequestModel(

        @SerializedName("pid") val pid: String = "",
        @SerializedName("dog_name") val dog_name: String = "",
        @SerializedName("dog_breed") val dog_breed: String = "",
        @SerializedName("dog_pic") val dog_pic: String = "",
        @SerializedName("dog_status") val dog_status: String = "",
        @SerializedName("dog_birth") val dog_birth: String = "",
        @SerializedName("dog_description") val dog_description: String = ""
)