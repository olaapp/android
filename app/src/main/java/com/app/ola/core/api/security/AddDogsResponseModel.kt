package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class AddDogsResponseModel(

        @SerializedName("dog_id") val dog_id: String = "",
        @SerializedName("req_id") val req_id: String = "",
        @SerializedName("id") val id: String = ""

)