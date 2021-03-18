package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class InnovationFormsListModel(
    @SerializedName("data")
    var `data`: List<DataInnovationForm>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataInnovationForm(
    @SerializedName("actual_step")
    var actualStep: Int?,
    @SerializedName("body_content")
    var bodyContent: String?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("is_visible")
    var isVisible: Boolean?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: Int?
)