package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class DepartmentsModel(
    @SerializedName("data")
    var `data`: List<departmentsData>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class departmentsData (
    @SerializedName("code")
    var code: Any?,
    @SerializedName("created_at")
    var createdAt: Any?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
)