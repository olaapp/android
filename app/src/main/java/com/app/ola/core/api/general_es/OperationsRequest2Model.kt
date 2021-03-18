package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class OperationsRequest2Model(
    @SerializedName("category")
    var category: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("dt_end")
    var dtEnd: String?,
    @SerializedName("dt_start")
    var dtStart: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("nic")
    var nic: String?,
    @SerializedName("operation")
    var operation: String?
)