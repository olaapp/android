package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequestModel(

        @SerializedName("ppicture_id") val ppicture_id: String = "",
        @SerializedName("pid") val pid: String = ""

)