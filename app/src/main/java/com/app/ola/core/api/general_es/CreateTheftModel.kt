package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class CreateTheftModel(
    @SerializedName("address_reference")
    var addressReference: String?,
    @SerializedName("city_id")
    var cityId: String?,
    @SerializedName("department_id")
    var departmentId: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("lat")
    var lat: String?,
    @SerializedName("lng")
    var lng: String?,
    @SerializedName("reason_id")
    var reasonId: String?,
    @SerializedName("schedule_id")
    var scheduleId: String?,
    @SerializedName("pics_num")
    var pics_num: String?
)