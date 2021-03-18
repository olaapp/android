package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class MyTheftReportsModel(
    @SerializedName("data")
    var `data`: List<DataTheft>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataTheft(
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
    @SerializedName("id")
    var id: Int?,
    @SerializedName("images")
    var images: List<ImageTheft>?,
    @SerializedName("lat")
    var lat: String?,
    @SerializedName("lng")
    var lng: String?,
    @SerializedName("pics_num")
    var picsNum: Any?,
    @SerializedName("reason_id")
    var reasonId: Int?,
    @SerializedName("report_comment")
    var reportComment: String?,
    @SerializedName("schedule_id")
    var scheduleId: Int?,
    @SerializedName("theft_description")
    var theftDescription: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: String?,
    @SerializedName("depto_name")
    var depto_name: String?,
    @SerializedName("muni_name")
    var muni_name: String?
)

data class ImageTheft(
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