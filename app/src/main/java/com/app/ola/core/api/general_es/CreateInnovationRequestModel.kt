package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class CreateInnovationRequestModel(
    @SerializedName("body_content")
    var body_content: String
)