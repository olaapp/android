package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class MySettingsModelRead(
    @SerializedName("data")
    var `data`: List<DataSettings>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataSettings(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("pref_name")
    var prefName: String?,
    @SerializedName("pref_value")
    var prefValue: Boolean?,
    @SerializedName("user_id")
    var userId: Int?
)