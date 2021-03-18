package com.app.ola.core.api.security
import com.google.gson.annotations.SerializedName


data class CanloverAssignedDataModel(
    @SerializedName("avg_stars")
    var avgStars: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("pic_url")
    var picUrl: String?,
    @SerializedName("total_trips")
    var totalTrips: String?
)