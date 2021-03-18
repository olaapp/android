package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class MyOperationsModel(
    @SerializedName("data")
    var `data`: List<DataOperations>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataOperations(
    @SerializedName("affected")
    var affected: String?,
    @SerializedName("category")
    var category: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("danger")
    var danger: String?,
    @SerializedName("dt_end")
    var dtEnd: String?,
    @SerializedName("dt_start")
    var dtStart: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("nic")
    var nic: String?,
    @SerializedName("open_code")
    var openCode: String?,
    @SerializedName("operation")
    var operation: String?,
    @SerializedName("operation_name")
    var operationName: String?,
    @SerializedName("status")
    var status: Any?
)