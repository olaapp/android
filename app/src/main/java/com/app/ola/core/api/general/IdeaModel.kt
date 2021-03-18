package com.app.ola.core.api.general

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class IdeaModel(
        @SerializedName("id") @PrimaryKey var id: Int = 0,
        @SerializedName("idea") var idea: String = "",
        @SerializedName("type") var type: Int = 0,
        @SerializedName("created_at") var createdAt: String = "",
        @SerializedName("updated_at") var updatedAt: String = ""
) : RealmObject()