package com.app.ola.core.api.general_es
import com.google.gson.annotations.SerializedName


data class NewsModel(
    @SerializedName("data")
    var `data`: List<DataNews>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataNews(
    @SerializedName("body_content")
    var bodyContent: String?,
    @SerializedName("content_url")
    var contentUrl: String?,
    @SerializedName("created_at")
    var createdAt: Any?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("is_visible")
    var isVisible: Boolean?,
    @SerializedName("pic_url")
    var picUrl: String?,
    @SerializedName("title")
    var title: String?
)