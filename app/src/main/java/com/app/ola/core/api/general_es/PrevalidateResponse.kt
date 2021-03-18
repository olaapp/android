package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class PrevalidateResponse(
    @SerializedName("is_valid")
    var isValid: Boolean?
)