package com.app.ola.core.api.security

import com.google.gson.annotations.SerializedName

data class NewTripResponseModel(

        @SerializedName("user_id") val user_id: String = "",
        @SerializedName("plan_id") val plan_id: String = "",
        @SerializedName("comment") val comment: String = "",
        @SerializedName("trip_mins") val trip_mins: String = "",
        @SerializedName("trip_days") val trip_days: String = "",
        @SerializedName("startdate") val startdate: String = "",
        @SerializedName("time") val time: String = "",
        @SerializedName("address_id") val address_id: String = "",
        @SerializedName("phone_id") val phone_id: String = "",
        @SerializedName("card_id") val card_id: String = "",
        @SerializedName("id") val id: String = ""

)