package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class UpdatePrefModel(
    @SerializedName("pref_id")
    var prefId: String?,
    @SerializedName("pref_val")
    var prefVal: Boolean?
)