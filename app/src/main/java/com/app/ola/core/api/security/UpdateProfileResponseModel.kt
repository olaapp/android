package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponseModel(
    @SerializedName("id")
    var id: String?,
    @SerializedName("pid")
    var pid: String?,
    @SerializedName("ppicture_id")
    var ppictureId: String?
)