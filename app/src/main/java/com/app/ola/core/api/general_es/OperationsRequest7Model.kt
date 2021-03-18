package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class OperationsRequest7Model(
    @SerializedName("affected_neighborhood")
    var affectedNeighborhood: String?,
    @SerializedName("category")
    var category: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("danger_id")
    var dangerId: String?,
    @SerializedName("danger_name")
    var dangerName: String?,
    @SerializedName("nic")
    var nic: String?,
    @SerializedName("operation")
    var operation: String?
)