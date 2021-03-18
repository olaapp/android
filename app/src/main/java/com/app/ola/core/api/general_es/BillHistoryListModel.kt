package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class BillHistoryListModel(
    @SerializedName("data")
    var `data`: List<DataHistory>?,
    @SerializedName("ErrorCode")
    var errorCode: String?,
    @SerializedName("ErrorMsg")
    var errorMsg: String?
)

data class DataHistory(
    @SerializedName("csmo_fact")
    var csmoFact: String?,
    @SerializedName("f_fact")
    var fFact: String?,
    @SerializedName("imp_fact")
    var impFact: String?,
    @SerializedName("nis_rad")
    var nisRad: String?,
    @SerializedName("num_recibo")
    var numRecibo: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("url_recibo")
    var urlRecibo: String?
)