package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class NewPetResponseModel(
    @SerializedName("dog_birth")
    var dogBirth: String?,
    @SerializedName("dog_breed")
    var dogBreed: String?,
    @SerializedName("dog_description")
    var dogDescription: String?,
    @SerializedName("dog_name")
    var dogName: String?,
    @SerializedName("dog_pic")
    var dogPic: String?,
    @SerializedName("dog_status")
    var dogStatus: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("pid")
    var pid: String?
)