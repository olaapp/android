package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class getNICBillStatusModelResponse(
    @SerializedName("data")
    var `data`: DataNICBill?,
    @SerializedName("ErrorCode")
    var errorCode: String?,
    @SerializedName("ErrorMsg")
    var errorMsg: String?,
    @SerializedName("ulr_not_debt")
    var ulr_not_debt: String?
)

data class DataNICBill(
    @SerializedName("alcaldia")
    var alcaldia: String?,
    @SerializedName("cliente")
    var cliente: String?,
    @SerializedName("comision_pagadito")
    var comisionPagadito: String?,
    @SerializedName("consumo")
    var consumo: String?,
    @SerializedName("csmo_fact")
    var csmoFact: String?,
    @SerializedName("empresa")
    var empresa: String?,
    @SerializedName("estado")
    var estado: String?,
    @SerializedName("estado_transaccion")
    var estadoTransaccion: Any?,
    @SerializedName("f_emision")
    var fEmision: String?,
    @SerializedName("f_fact")
    var fFact: String?,
    @SerializedName("f_vencimiento")
    var fVencimiento: String?,
    @SerializedName("fecha_facturado_fin")
    var fechaFacturadoFin: String?,
    @SerializedName("fecha_facturado_inicio")
    var fechaFacturadoInicio: String?,
    @SerializedName("id_cobro")
    var idCobro: String?,
    @SerializedName("npe")
    var npe: String?,
    @SerializedName("otros_servicios")
    var otrosServicios: String?,
    @SerializedName("reconexion")
    var reconexion: String?,
    @SerializedName("saldo_pagar")
    var saldoPagar: String?,
    @SerializedName("url")
    var url: String?
)