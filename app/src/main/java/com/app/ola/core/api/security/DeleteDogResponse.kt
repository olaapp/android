package com.app.ola.core.api.security
import com.google.gson.annotations.SerializedName


data class DeleteDogResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("pid")
    var pid: String?
)