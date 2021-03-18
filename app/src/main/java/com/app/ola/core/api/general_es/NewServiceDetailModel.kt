package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class NewServiceDetailModel(
    @SerializedName("data")
    var `data`: List<DataNST>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataNST(
    @SerializedName("dt_updated")
    var dtUpdated: String?,
    @SerializedName("holder_adress")
    var holderAdress: String?,
    @SerializedName("holder_doc_number")
    var holderDocNumber: String?,
    @SerializedName("holder_doc_type")
    var holderDocType: String?,
    @SerializedName("holder_email")
    var holderEmail: String?,
    @SerializedName("holder_fax")
    var holderFax: String?,
    @SerializedName("holder_fiscal_number")
    var holderFiscalNumber: String?,
    @SerializedName("holder_fiscal_status")
    var holderFiscalStatus: String?,
    @SerializedName("holder_lastname")
    var holderLastname: String?,
    @SerializedName("holder_mobile")
    var holderMobile: String?,
    @SerializedName("holder_names")
    var holderNames: String?,
    @SerializedName("holder_nit")
    var holderNit: String?,
    @SerializedName("holder_phone")
    var holderPhone: String?,
    @SerializedName("holder_profession")
    var holderProfession: String?,
    @SerializedName("holder_slastname")
    var holderSlastname: String?,
    @SerializedName("holder_type")
    var holderType: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("lr_adress")
    var lrAdress: String?,
    @SerializedName("lr_doc_number")
    var lrDocNumber: String?,
    @SerializedName("lr_doc_type")
    var lrDocType: String?,
    @SerializedName("lr_lastname")
    var lrLastname: String?,
    @SerializedName("lr_names")
    var lrNames: String?,
    @SerializedName("lr_nit")
    var lrNit: String?,
    @SerializedName("lr_profession")
    var lrProfession: String?,
    @SerializedName("lr_slastname")
    var lrSlastname: String?,
    @SerializedName("url_doc_1")
    var urlDoc1: String?,
    @SerializedName("url_doc_2")
    var urlDoc2: String?,
    @SerializedName("url_doc_3")
    var urlDoc3: String?,
    @SerializedName("url_doc_4")
    var urlDoc4: String?
)