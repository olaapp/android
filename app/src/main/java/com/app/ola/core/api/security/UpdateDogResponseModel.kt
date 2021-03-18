package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class UpdateDogResponseModel(
    @SerializedName("id")
    var id: String?,
    @SerializedName("pbir")
    var pbir: String?,
    @SerializedName("pbree")
    var pbree: String?,
    @SerializedName("pdes")
    var pdes: String?,
    @SerializedName("pid")
    var pid: String?
)