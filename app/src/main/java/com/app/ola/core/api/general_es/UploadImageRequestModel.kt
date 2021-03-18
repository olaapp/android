package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class UploadImageRequestModel(
    @SerializedName("pic_url")
    var picUrl: String?,
    @SerializedName("row_reference")
    var rowReference: String?,
    @SerializedName("type")
    var type: String?
)