package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class CanlovePlansModel(
    @SerializedName("flag")
    var flag: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("price")
    var price: String?,
    @SerializedName("price_per_dog_30min")
    var pricePerDog30min: String?,
    @SerializedName("price_per_dog_60min")
    var pricePerDog60min: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("trip_per_week")
    var tripPerWeek: String?,
    @SerializedName("min_plan")
    var min_plan: String?,
    @SerializedName("calculated_price")
    var calculated_price: String?
)