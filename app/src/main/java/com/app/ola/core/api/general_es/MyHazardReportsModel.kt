package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class MyHazardReportsModel(
    @SerializedName("data")
    var `data`: List<DataHazard>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataHazard(
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
    var images: List<ImageTH>?,
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
    var userId: String?,
    @SerializedName("muni_name")
    var muni_name: String?,
    @SerializedName("depto_name")
    var depto_name: String?
)

data class ImageTH(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("pic_url")
    var picUrl: String?,
    @SerializedName("row_reference")
    var rowReference: Int?,
    @SerializedName("type")
    var type: String?
)