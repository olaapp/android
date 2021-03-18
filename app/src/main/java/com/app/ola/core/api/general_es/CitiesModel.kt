package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class CitiesModel(
    @SerializedName("data")
    var `data`: List<DataCity>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataCity(
    @SerializedName("code")
    var code: Any?,
    @SerializedName("created_at")
    var createdAt: Any?,
    @SerializedName("department_id")
    var departmentId: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
)