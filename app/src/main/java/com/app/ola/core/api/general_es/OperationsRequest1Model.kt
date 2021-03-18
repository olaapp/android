package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class OperationsRequest1Model(
    @SerializedName("category")
    var category: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("nic")
    var nic: String?,
    @SerializedName("operation")
    var operation: String?
)