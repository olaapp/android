package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class MyNotificationsModel(
    @SerializedName("data")
    var `data`: List<DataNotif>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataNotif(
    @SerializedName("created_dt")
    var createdDt: Any?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("notification_body")
    var notificationBody: String?,
    @SerializedName("notification_title")
    var notificationTitle: String?,
    @SerializedName("status")
    var status: Boolean?,
    @SerializedName("updated_dt")
    var updatedDt: Any?,
    @SerializedName("user_id")
    var userId: Int?
)