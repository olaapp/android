package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName

data class HazardDetailsDBModel(
    @SerializedName("data")
    var `data`: List<Data>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class Data(
    @SerializedName("address_reference")
    var addressReference: String?,
    @SerializedName("city_id")
    var cityId: Int?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("current_step")
    var currentStep: Int?,
    @SerializedName("department_id")
    var departmentId: Int?,
    @SerializedName("hazard_description")
    var hazardDescription: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("images")
    var images: List<Any>?,
    @SerializedName("lat")
    var lat: String?,
    @SerializedName("lng")
    var lng: String?,
    @SerializedName("open_code")
    var openCode: String?,
    @SerializedName("pics_num")
    var picsNum: Int?,
    @SerializedName("reason_id")
    var reasonId: Int?,
    @SerializedName("report_comment")
    var reportComment: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: String?

    ,
    @SerializedName("depto_name")
    var depto_name: String?
    ,
    @SerializedName("muni_name")
    var muni_name: String?
)